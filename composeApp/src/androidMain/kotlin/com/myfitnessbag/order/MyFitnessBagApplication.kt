package com.myfitnessbag.order

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.net.Uri
import androidx.core.content.getSystemService
import com.myfitnessbag.order.core.util.constants.AppShortcuts
import com.myfitnessbag.order.platform_module.KoinInit


class MyFitnessBagApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        KoinInit(
            this@MyFitnessBagApplication,
        ).init()
    }

}


