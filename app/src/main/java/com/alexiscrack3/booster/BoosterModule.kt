package com.alexiscrack3.booster

import android.content.Context
import com.alexiscrack3.booster.vocabulary.details.entryDetailsModule
import com.alexiscrack3.booster.vocabulary.entries.entriesModule
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

object BoosterModule {

    fun init(context: Context) {
        startKoin {
            // use AndroidLogger as Koin Logger - default Level.INFO
            androidLogger()

            // use the Android context given there
            androidContext(context)

            val routerModule = module {
                single {
                    BoosterRouter()
                }
            }

            val firestoreModule = module {
                single {
                    FirebaseFirestore.getInstance()
                }
            }

            val moduleList = listOf(
                networkModule,
                routerModule,
                firestoreModule,
                entriesModule,
                entryDetailsModule
            )
            modules(moduleList)
        }
    }
}
