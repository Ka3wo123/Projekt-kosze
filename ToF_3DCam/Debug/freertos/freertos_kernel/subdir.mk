################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
C_SRCS += \
../freertos/freertos_kernel/croutine.c \
../freertos/freertos_kernel/event_groups.c \
../freertos/freertos_kernel/list.c \
../freertos/freertos_kernel/queue.c \
../freertos/freertos_kernel/stream_buffer.c \
../freertos/freertos_kernel/tasks.c \
../freertos/freertos_kernel/timers.c 

C_DEPS += \
./freertos/freertos_kernel/croutine.d \
./freertos/freertos_kernel/event_groups.d \
./freertos/freertos_kernel/list.d \
./freertos/freertos_kernel/queue.d \
./freertos/freertos_kernel/stream_buffer.d \
./freertos/freertos_kernel/tasks.d \
./freertos/freertos_kernel/timers.d 

OBJS += \
./freertos/freertos_kernel/croutine.o \
./freertos/freertos_kernel/event_groups.o \
./freertos/freertos_kernel/list.o \
./freertos/freertos_kernel/queue.o \
./freertos/freertos_kernel/stream_buffer.o \
./freertos/freertos_kernel/tasks.o \
./freertos/freertos_kernel/timers.o 


# Each subdirectory must supply rules for building sources it contributes
freertos/freertos_kernel/%.o: ../freertos/freertos_kernel/%.c freertos/freertos_kernel/subdir.mk
	@echo 'Building file: $<'
	@echo 'Invoking: MCU C Compiler'
	arm-none-eabi-gcc -D__REDLIB__ -DCPU_LPC55S69JBD100 -DCPU_LPC55S69JBD100_cm33 -DCPU_LPC55S69JBD100_cm33_core0 -DSDK_OS_BAREMETAL -DSDK_DEBUGCONSOLE=1 -DPRINTF_FLOAT_ENABLE=1 -DSERIAL_PORT_TYPE_UART=1 -D__MCUXPRESSO -D__USE_CMSIS -DDEBUG -DSDK_OS_FREE_RTOS -I"D:\pobrane\Trash_v1\ToF_3DCam\drivers" -I"D:\pobrane\Trash_v1\ToF_3DCam\device" -I"D:\pobrane\Trash_v1\ToF_3DCam\CMSIS" -I"D:\pobrane\Trash_v1\ToF_3DCam\utilities" -I"D:\pobrane\Trash_v1\ToF_3DCam\component\uart" -I"D:\pobrane\Trash_v1\ToF_3DCam\component\serial_manager" -I"D:\pobrane\Trash_v1\ToF_3DCam\component\lists" -I"D:\pobrane\Trash_v1\ToF_3DCam\board" -I"D:\pobrane\Trash_v1\ToF_3DCam\startup" -I"D:\pobrane\Trash_v1\ToF_3DCam\freertos\freertos_kernel\include" -I"D:\pobrane\Trash_v1\ToF_3DCam\drivers\freertos" -I"D:\pobrane\Trash_v1\ToF_3DCam\component\serial_mwm" -I"D:\pobrane\Trash_v1\ToF_3DCam\source" -I"D:\pobrane\Trash_v1\ToF_3DCam\freertos\freertos_kernel\portable\GCC\ARM_CM33_NTZ\non_secure" -I"D:\pobrane\Trash_v1\ToF_3DCam\drivers" -I"D:\pobrane\Trash_v1\ToF_3DCam\device" -I"D:\pobrane\Trash_v1\ToF_3DCam\CMSIS" -I"D:\pobrane\Trash_v1\ToF_3DCam\utilities" -I"D:\pobrane\Trash_v1\ToF_3DCam\component\uart" -I"D:\pobrane\Trash_v1\ToF_3DCam\component\serial_manager" -I"D:\pobrane\Trash_v1\ToF_3DCam\component\lists" -I"D:\pobrane\Trash_v1\ToF_3DCam\board" -I"D:\pobrane\Trash_v1\ToF_3DCam\startup" -I"D:\pobrane\Trash_v1\ToF_3DCam\freertos\freertos_kernel\include" -I"D:\pobrane\Trash_v1\ToF_3DCam\drivers\freertos" -I"D:\pobrane\Trash_v1\ToF_3DCam\component\serial_mwm" -I"D:\pobrane\Trash_v1\ToF_3DCam\source" -I"D:\pobrane\Trash_v1\ToF_3DCam\freertos\freertos_kernel\portable\GCC\ARM_CM33_NTZ\non_secure" -O0 -fno-common -g3 -Wall -c -ffunction-sections -fdata-sections -ffreestanding -fno-builtin -fmerge-constants -fmacro-prefix-map="$(<D)/"= -mcpu=cortex-m33 -mfpu=fpv5-sp-d16 -mfloat-abi=hard -mthumb -D__REDLIB__ -fstack-usage -specs=redlib.specs -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.o)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


clean: clean-freertos-2f-freertos_kernel

clean-freertos-2f-freertos_kernel:
	-$(RM) ./freertos/freertos_kernel/croutine.d ./freertos/freertos_kernel/croutine.o ./freertos/freertos_kernel/event_groups.d ./freertos/freertos_kernel/event_groups.o ./freertos/freertos_kernel/list.d ./freertos/freertos_kernel/list.o ./freertos/freertos_kernel/queue.d ./freertos/freertos_kernel/queue.o ./freertos/freertos_kernel/stream_buffer.d ./freertos/freertos_kernel/stream_buffer.o ./freertos/freertos_kernel/tasks.d ./freertos/freertos_kernel/tasks.o ./freertos/freertos_kernel/timers.d ./freertos/freertos_kernel/timers.o

.PHONY: clean-freertos-2f-freertos_kernel

