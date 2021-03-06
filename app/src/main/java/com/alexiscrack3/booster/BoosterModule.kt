package com.alexiscrack3.booster

import com.alexiscrack3.booster.home.homeModule
import com.alexiscrack3.booster.entries.details.entryDetailsModule
import com.alexiscrack3.booster.entries.list.entriesModule
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object BoosterModule {

    fun init() {
        val firestoreModule = module {
            single {
                FirebaseFirestore.getInstance()
            }
        }

        val moduleList = listOf(
            networkModule,
            firestoreModule,
            homeModule,
            entriesModule,
            entryDetailsModule
        )
        loadKoinModules(moduleList)
    }
}
