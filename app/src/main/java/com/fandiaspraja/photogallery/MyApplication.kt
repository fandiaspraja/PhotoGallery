package com.fandiaspraja.photogallery

import android.app.Application
import com.fandiaspraja.photogallery.core.di.databaseModule
import com.fandiaspraja.photogallery.core.di.networkModule
import com.fandiaspraja.photogallery.core.di.repositoryModule
import com.fandiaspraja.photogallery.di.userCaseModule
import com.fandiaspraja.photogallery.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    userCaseModule,
                    viewModelModule
                )
            )
        }
    }
}