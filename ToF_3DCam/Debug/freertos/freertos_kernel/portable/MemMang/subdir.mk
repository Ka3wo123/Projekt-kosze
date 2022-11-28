################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
C_SRCS += \
../freertos/freertos_kernel/portable/MemMang/heap_4.c 

C_DEPS += \
./freertos/freertos_kernel/portable/MemMang/heap_4.d 

OBJS += \
./freertos/freertos_kernel/portable/MemMang/heap_4.o 


# Each subdirectory must supply rules for building sources it contributes
freertos/freertos_kernel/portable/MemMang/%.o: ../freertos/freertos_kernel/portable/MemMang/%.c freertos/freertos_kernel/portable/MemMang/subdir.mk
	@echo 'Building file: $<'
	@echo 'Invoking: MCU C Compiler'
	arm-none-eabi-gcc -D__REDLIB__ -DCPU_LPC55S69JBD100 -DCPU_LPC55S69JBD100_cm33 -DCPU_LPC55S69JBD100_cm33_core0 -DSDK_OS_BAREMETAL -DSDK_DEBUGCONSOLE=1 -DPRINTF_FLOAT_ENABLE=1 -DSERIAL_PORT_TYPE_UART=1 -D__MCUXPRESSO -D__USE_CMSIS -DDEBUG -DSDK_OS_FREE_RTOS -I"D:\pobrane\Trash_v1\ToF_3DCam\drivers" -I"D:\pobrane\Trash_v1\ToF_3DCam\device" -I"D:\pobrane\Trash_v1\ToF_3DCam\CMSIS" -I"D:\pobrane\Trash_v1\ToF_3DCam\utilities" -I"D:\pobrane\Trash_v1\ToF_3DCam\component\uart" -I"D:\pobrane\Trash_v1\ToF_3DCam\component\serial_manager" -I"D:\pobrane\Trash_v1\ToF_3DCam\component\lists" -I"D:\pobrane\Trash_v1\ToF_3DCam\board" -I"D:\pobrane\Trash_v1\ToF_3DCam\startup" -I"D:\pobrane\Trash_v1\ToF_3DCam\freertos\freertos_kernel\include" -I"D:\pobrane\Trash_v1\ToF_3DCam\drivers\freertos" -I"D:\pobrane\Trash_v1\ToF_3DCam\component\serial_mwm" -I"D:\pobrane\Trash_v1\ToF_3DCam\source" -I"D:\pobrane\Trash_v1\ToF_3DCam\freertos\freertos_kernel\portable\GCC\ARM_CM33_NTZ\non_secure" -I"D:\pobrane\Trash_v1\ToF_3DCam\drivers" -I"D:\pobrane\Trash_v1\ToF_3DCam\device" -I"D:\pobrane\Trash_v1\ToF_3DCam\CMSIS" -I"D:\pobrane\Trash_v1\ToF_3DCam\utilities" -I"D:\pobrane\Trash_v1\ToF_3DCam\component\uart" -I"D:\pobrane\Trash_v1\ToF_3DCam\component\serial_manager" -I"D:\pobrane\Trash_v1\ToF_3DCam\component\lists" -I"D:\pobrane\Trash_v1\ToF_3DCam\board" -I"D:\pobrane\Trash_v1\ToF_3DCam\startup" -I"D:\pobrane\Trash_v1\ToF_3DCam\freertos\freertos_kernel\include" -I"D:\pobrane\Trash_v1\ToF_3DCam\drivers\freertos" -I"D:\pobrane\Trash_v1\ToF_3DCam\component\serial_mwm" -I"D:\pobrane\Trash_v1\ToF_3DCam\source" -I"D:\pobrane\Trash_v1\ToF_3DCam\freertos\freertos_kernel\portable\GCC\ARM_CM33_NTZ\non_secure" -O0 -fno-common -g3 -Wall -c -ffunction-sections -fdata-sections -ffreestanding -fno-builtin -fmerge-constants -fmacro-prefix-map="$(<D)/"= -mcpu=cortex-m33 -mfpu=fpv5-sp-d16 -mfloat-abi=hard -mthumb -D__REDLIB__ -fstack-usage -specs=redlib.specs -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.o)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


clean: clean-freertos-2f-freertos_kernel-2f-portable-2f-MemMang

clean-freertos-2f-freertos_kernel-2f-portable-2f-MemMang:
	-$(RM) ./freertos/freertos_kernel/portable/MemMang/heap_4.d ./freertos/freertos_kernel/portable/MemMang/heap_4.o

.PHONY: clean-freertos-2f-freertos_kernel-2f-portable-2f-MemMang

