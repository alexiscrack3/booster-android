package com.alexiscrack3.booster.vocabulary

import com.alexiscrack3.booster.models.Entry
import com.google.firebase.firestore.QuerySnapshot

class VocabularyMapper {

    fun map(querySnapshot: QuerySnapshot): List<Entry> {
        return querySnapshot.map {
            val data = it.data
            Entry(
                data["headword"] as String,
                data["class"] as String,
                null, //data["pronunciation"] as String,
                data["definitions"] as List<String>
            )
        }
    }
}
