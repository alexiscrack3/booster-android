package com.alexiscrack3.booster.vocabulary

import com.alexiscrack3.booster.models.Entry
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot

class EntriesMapper {

    fun map(querySnapshot: QuerySnapshot): List<Entry> {
        return querySnapshot.map {
            val data = it.data
            Entry(
                it.id,
                data["headword"] as String,
                data["class"] as String,
                null, //data["pronunciation"] as String,
                data["definitions"] as List<String>
            )
        }
    }

    fun map(documentSnapshot: DocumentSnapshot): Entry {
        val data = documentSnapshot.data!!
        return Entry(
            documentSnapshot.id,
            data["headword"] as String,
            data["class"] as String,
            null, //data["pronunciation"] as String,
            data["definitions"] as List<String>
        )
    }
}
