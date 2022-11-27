/*
 * Copyright 2016-2022 NXP
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * o Redistributions of source code must retain the above copyright notice, this list
 *   of conditions and the following disclaimer.
 *
 * o Redistributions in binary form must reproduce the above copyright notice, this
 *   list of conditions and the following disclaimer in the documentation and/or
 *   other materials provided with the distribution.
 *
 * o Neither the name of NXP Semiconductor, Inc. nor the names of its
 *   contributors may be used to endorse or promote products derived from this
 *   software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/**
 * @file    ToF_3DCam.c
 * @brief   Application entry point.
 */
#include <stdio.h>
#include "board.h"
#include "peripherals.h"
#include "pin_mux.h"
#include "clock_config.h"
#include "LPC55S69_cm33_core0.h"
#include "fsl_debug_console.h"
#include "wlan_mwm.h"
#include "lcd.h"
#include "back.h"
#include "bmp280.h"
#include "vl53l5cx_api.h"
#include "platform.h"
/*
AP_SECURITY_MODE:
0 - Open
1 - WEP (Open mode)
2 - WEP (Shared mode)
3 - WPA-PSK
4 - WPA2-PSK
9 - WPA3-SAE
 ----------------------------------------------------------------------------*/
#define AP_SSID "redmi"
#define AP_PASSPHRASE "testtest"
#define AP_SECURITY_MODE "4"


#define STR_BUFFER_LEN 128
#define CDE_BUFFER_LEN 64

#define TEMP_MIN -40
#define TEMP_MAX 85
#define PRES_MIN 900
#define PRES_MAX 1100
#define HUMI_MIN 10
#define HUMI_MAX 100
/*******************************************************************************
 * Variables
 ******************************************************************************/
char g_bufferRX[RXD_BUFFER_LEN]={0}; // HTTP RX Buffer
char g_bufferTX[TXD_BUFFER_LEN]={0}; // HTTP TX Buffer
char g_sbuffer[STR_BUFFER_LEN]; // Text Buffer
BMP280_HandleTypedef bmp280;
/*******************************************************************************
 * Prototypes
 ******************************************************************************/
#ifndef MSEC_TO_TICK
#define MSEC_TO_TICK(msec) ((uint32_t)(msec) * (uint32_t)configTICK_RATE_HZ / 1000uL)
#endif
/*******************************************************************************
 * Code
 ******************************************************************************/
void draw_bars(uint16_t px, uint16_t py, uint16_t w, uint16_t h, uint16_t fcolor, uint16_t bcolor, float min, float max, float data)
{
	//frame
	LCD_Draw_Line(px, py, px+w, py, fcolor);
	LCD_Draw_Line(px, py+h, px+w, py+h, fcolor);
	LCD_Draw_Line(px, py, px, py+h, fcolor);
	LCD_Draw_Line(px+w, py, px+w, py+h, fcolor);
	//bar
	px+=2;
	py+=2;
	w-=4;
	h-=4;
	float range = abs(max - min);
	int v=w*((data - min) / range);
	for(int x=0; x<=v; x++)
		LCD_Draw_Line(px+x, py, px+x, py+h, bcolor);
}
void main_task(void *pvParameters) {
	LCD_Puts(10, 30, "Connecting to WiFi...", 0xFF00);
	LCD_GramRefresh();
	wlan_init(AP_SSID, AP_PASSPHRASE , AP_SECURITY_MODE);
	LCD_Clear(0x0841);
	sprintf(g_sbuffer, "WiFi: %s", AP_SSID);
	LCD_Puts(10, 30, g_sbuffer, 0xFF00);
	LCD_GramRefresh();
	vTaskDelay(MSEC_TO_TICK(1000));
	char codebuffer[CDE_BUFFER_LEN]={0};
	uint8_t counter=0;

	float pressure=0, temperature=0, humidity=0;
	while (1) {
		bmp280_read_float(&bmp280, &temperature, &pressure, &humidity);
		LCD_Set_Bitmap((uint16_t*)back_160x128);
		sprintf(g_sbuffer,"Temperature: %.1f C", temperature);
		LCD_Puts(10, 50,g_sbuffer, 0xFFFF);
		draw_bars(10, 60, 140, 8, 0xFFFF, 0xF800, TEMP_MIN, TEMP_MAX, temperature);
		sprintf(g_sbuffer,"Humidity: %.1f %%", humidity);
		LCD_Puts(10, 75, g_sbuffer, 0xFFFF);
		draw_bars(10, 85, 140, 8, 0xFFFF, 0x0FF0, HUMI_MIN, HUMI_MAX, humidity);
		sprintf(g_sbuffer,"Pressure: %.1f hPa", pressure/100);
		LCD_Puts(10, 100, g_sbuffer, 0xFFFF);
		draw_bars(10, 110, 140, 8, 0xFFFF, 0xFF00, PRES_MIN, PRES_MAX, pressure/100);
		if(counter==0) {
			sprintf(g_bufferTX,
					"api.thingspeak.com/update?api_key=5M2JJR5VFXS1JOKB&field1=%.2f&field2=%.2f&field3=%.2f",
					temperature,
					pressure/100,
					humidity);
			http_GET(g_bufferTX, g_bufferRX);
			http_head_parser(g_bufferRX, codebuffer, "HTTP");
			sprintf(g_sbuffer,"HTTP%s", codebuffer);
			LCD_Puts(10, 31, g_sbuffer, 0x0FFF);
		}
		counter++;
		if(counter >= 15) {
			counter=0;
		}
		LCD_GramRefresh();
		vTaskDelay(MSEC_TO_TICK(1000));
	}
}
/*
 * @brief Application entry point.
 */
int main(void) {
	/* Init board hardware. */
	BOARD_InitBootPins();
	BOARD_InitBootClocks();
	BOARD_InitBootPeripherals();
#ifndef BOARD_INIT_DEBUG_CONSOLE_PERIPHERAL
	/* Init FSL debug console. */
	BOARD_InitDebugConsole();
#endif
	LCD_Init(FLEXCOMM3_PERIPHERAL);
	LCD_Clear(0x0841);
	bmp280_init_default_params(&bmp280.params);
	bmp280.addr = BMP280_I2C_ADDRESS_0;
	bmp280.i2c = FLEXCOMM1_PERIPHERAL;
	if (!bmp280_init(&bmp280, &bmp280.params)) {
		LCD_Puts(10, 30, "BMP280 init failed!", 0xF800);
		LCD_GramRefresh();
		while(1)
			;
	}
	if (xTaskCreate(main_task, "main_task", 350, NULL, MAIN_TASK_PRIORITY, NULL) != pdPASS) {
		PRINTF("Task creation failed!.\r\n");
		while (1)
			;
	}
	vTaskStartScheduler();
	while(1) {
	}
	return 0 ;
}
