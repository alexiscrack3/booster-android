package com.alexiscrack3.booster

import android.app.Application
import com.alexiscrack3.booster.vocabulary.vocabularyModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

object BoosterModule {

    fun init(application: Application) {
        startKoin {
            // use AndroidLogger as Koin Logger - default Level.INFO
            androidLogger()

            // use the Android context given there
            androidContext(application)

            val routerModule = module {
                single {
                    BoosterRouter()
                }
            }

            val moduleList = listOf(
                networkModule,
                routerModule,
                vocabularyModule
            )
            modules(moduleList)
        }
    }
}
