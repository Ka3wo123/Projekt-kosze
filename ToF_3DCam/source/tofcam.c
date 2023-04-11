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
#include <string.h>
#include "board.h"
#include "peripherals.h"
#include "pin_mux.h"
#include "clock_config.h"
#include "LPC55S69_cm33_core0.h"
#include "fsl_debug_console.h"

#include "vl53l5cx_api.h"
#include "platform.h"
#include "bmp280.h"
#include "wlan_mwm.h"
#include "cJSON.h"

int status;
volatile int IntCount;
uint8_t p_data_ready;
VL53L5CX_Configuration 	Dev;
VL53L5CX_ResultsData 	Results;
uint8_t resolution, isAlive;
uint16_t idx;

#define AP_SSID "redmi"
#define AP_PASSPHRASE "testtest"
#define AP_SECURITY_MODE "4"
/*---------------------------------------------------------------------------*/
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


void main_task(void *pvParameters) {

	wlan_init(AP_SSID, AP_PASSPHRASE , AP_SECURITY_MODE);

	vTaskDelay(MSEC_TO_TICK(1000));
	char codebuffer[CDE_BUFFER_LEN]={0};
	uint8_t counter=0;

	const int MAT_SIZE = 64;
	int sum = 0;
	float pressure=0, temperature=0, humidity=0;
	int mean;

	while (1) {


		if(counter==0) {
			status = vl53l5cx_get_resolution(&Dev, &resolution);
			status = vl53l5cx_get_ranging_data(&Dev, &Results);
			bmp280_read_float(&bmp280, &temperature, &pressure, &humidity);

			for(int j = 0; j < resolution/8;j++){
				for(int i = 0; i < resolution/8;i++){
					sum += Results.distance_mm[(VL53L5CX_NB_TARGET_PER_ZONE * i)+(8*j)];
				}
			}

			mean = ((float)sum / MAT_SIZE);
			sum = 0;

			sprintf(g_bufferTX,
					"api.thingspeak.com/update?api_key=5M2JJR5VFXS1JOKB&field4=%d&field1=%.2f&field2=%.2f&field3=%.2f",
					mean,temperature, pressure, humidity );

			http_GET(g_bufferTX, g_bufferRX);
			http_head_parser(g_bufferRX, codebuffer, "HTTP");

			PRINTF("%s\n", g_bufferRX);
		}
		counter++;
		if(counter >= 15) {
			counter=0;
		}
		vTaskDelay(MSEC_TO_TICK(1000));
	}
}

void delay_ms(uint32_t delay)
{
	for(volatile int j=delay*100000; j > 0;j--);
}

void cbToF_Ready(pint_pin_int_t pintr, uint32_t pmatch_status) {

	status = vl53l5cx_get_resolution(&Dev, &resolution);
	status = vl53l5cx_get_ranging_data(&Dev, &Results);

	for(int j = 0; j < resolution/8;j++){
		for(int i = 0; i < resolution/8;i++){

			PRINTF("%4d ", Results.distance_mm[(VL53L5CX_NB_TARGET_PER_ZONE * i)+(8*j)]);
		}
		PRINTF("\r\n");
	}
	PRINTF("--------------------------------------\r\n");
}

/*
 * @brief   Application entry point.
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
	bmp280_init_default_params(&bmp280.params);
	bmp280.addr = BMP280_I2C_ADDRESS_0;
	bmp280.i2c = FLEXCOMM1_PERIPHERAL;
	if (!bmp280_init(&bmp280, &bmp280.params)) {
		while(1)
			;
	}

	Dev.platform.i2c = FLEXCOMM4_PERIPHERAL;
	Dev.platform.address = 0x29;


	GPIO_PinWrite(BOARD_TOFCAMPINS_TRST_GPIO, BOARD_TOFCAMPINS_TRST_PORT, BOARD_TOFCAMPINS_TRST_PIN, 0);
	delay_ms(20);

	GPIO_PinWrite(BOARD_TOFCAMPINS_TLPn_GPIO, BOARD_TOFCAMPINS_TLPn_PORT, BOARD_TOFCAMPINS_TLPn_PIN, 0);
	delay_ms(20);

	GPIO_PinWrite(BOARD_TOFCAMPINS_TLPn_GPIO, BOARD_TOFCAMPINS_TLPn_PORT, BOARD_TOFCAMPINS_TLPn_PIN, 1);
	delay_ms(20);


	status = vl53l5cx_is_alive(&Dev, &isAlive);
	if(!isAlive && status==0) {

		PRINTF("VL53L5CXV0 not detected at requested address (0x%x)\r\n", Dev.platform.address);
		return 1;
	} else {

		PRINTF("VL53L5CXV0 is Alive at address (0x%x)\r\n", Dev.platform.address);
	}

	PRINTF("Sensor initializing, please wait few seconds...\r\n");
	status = vl53l5cx_init(&Dev);
	status = vl53l5cx_set_resolution(&Dev, VL53L5CX_RESOLUTION_8X8);
	status = vl53l5cx_set_ranging_frequency_hz(&Dev, 15);				         // Set 15 Hz ranging frequency
	PRINTF("Status %d\r\n", status);
	status = vl53l5cx_set_ranging_mode(&Dev, VL53L5CX_RANGING_MODE_CONTINUOUS);  // Set mode continuous

	PRINTF("Ranging starts\r\n");
	status = vl53l5cx_start_ranging(&Dev);
	/* USER CODE END 2 */
	//PINT_EnableCallbackByIndex(PINT_PERIPHERAL, kPINT_PinInt0);
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
