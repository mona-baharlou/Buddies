package com.baharlou.buddies.app

import android.app.Application
import org.koin.core.context.startKoin

class BuddiesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            modules(applicationModule)
        }
    }
}