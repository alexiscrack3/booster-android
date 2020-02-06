package com.alexiscrack3.booster

import com.alexiscrack3.booster.home.homeModule
import com.alexiscrack3.booster.vocabulary.details.entryDetailsModule
import com.alexiscrack3.booster.vocabulary.entries.entriesModule
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object BoosterModule {

    fun init() {
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
            homeModule,
            entriesModule,
            entryDetailsModule
        )
        loadKoinModules(moduleList)
    }
}
