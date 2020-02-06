package com.alexiscrack3.booster.vocabulary

import com.alexiscrack3.booster.models.Entry
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot

class EntriesMapper {

    fun map(querySnapshot: QuerySnapshot): List<Entry> {
        val transform: (QueryDocumentSnapshot) -> Entry = { queryDocumentSnapshot ->
            queryDocumentSnapshot.asEntry()
        }
        return querySnapshot.map<QueryDocumentSnapshot, Entry>(transform)
    }

    fun map(documentSnapshot: DocumentSnapshot): Entry? {
        return documentSnapshot.asEntry()
    }

    private fun DocumentSnapshot.asEntry(): Entry {
        return Entry(
            this.id,
            this["headword"] as String,
            this["class"] as String,
            this["pronunciation"] as String,
            this["definitions"] as List<String>
        )
    }
}
