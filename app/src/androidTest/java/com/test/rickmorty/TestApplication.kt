package com.test.rickmorty

import android.app.Application
import com.test.rickmorty.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TestApplication : Application() {

    override fun onCreate() {
        startKoin {
            androidContext(this@TestApplication)
            modules(listOf(networkModule))
        }
    }

}