package com.subot.core.data.notification

import com.tweener.alarmee.configuration.AlarmeeIosPlatformConfiguration
import com.tweener.alarmee.configuration.AlarmeePlatformConfiguration

actual fun createAlarmeePlatformConfiguration(): AlarmeePlatformConfiguration =
    AlarmeeIosPlatformConfiguration
