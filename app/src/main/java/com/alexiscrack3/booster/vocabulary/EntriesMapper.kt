package com.alexiscrack3.booster.vocabulary

import com.alexiscrack3.booster.models.Entry
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot

class EntriesMapper {

    fun map(querySnapshot: QuerySnapshot): List<Entry> {
        return querySnapshot.map { queryDocumentSnapshot ->
            queryDocumentSnapshot.toEntry()
        }
    }

    fun map(documentSnapshot: DocumentSnapshot): Entry? {
        return documentSnapshot.toEntry()
    }

    private fun DocumentSnapshot.toEntry(): Entry {
        return Entry(
            this.id,
            this["headword"] as String,
            this["class"] as String,
            null, // this["pronunciation"] as String,
            this["definitions"] as List<String>
        )
    }
}
