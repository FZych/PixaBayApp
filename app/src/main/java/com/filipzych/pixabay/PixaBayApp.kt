package com.filipzych.pixabay

import android.app.Application
import com.filipzych.pixabay.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PixaBayApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PixaBayApp)
            modules(
                listOf(appModule)
            )
        }
    }
}