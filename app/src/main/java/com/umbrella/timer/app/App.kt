package com.umbrella.timer.app

import android.app.Application
import com.umbrella.timer.di.Di
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(Di.repositoryModule, Di.viewModelModule)
        }
    }
}