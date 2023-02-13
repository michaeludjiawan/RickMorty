package com.test.rickmorty

import android.app.Application
import com.test.rickmorty.di.characterModule
import com.test.rickmorty.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(listOf(networkModule, characterModule))
        }
    }
}