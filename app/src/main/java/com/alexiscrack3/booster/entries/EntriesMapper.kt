package com.alexiscrack3.booster.entries

import com.alexiscrack3.booster.models.Category
import com.alexiscrack3.booster.models.Entry
import com.alexiscrack3.booster.models.Sense
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot

class EntriesMapper {

    fun map(querySnapshot: QuerySnapshot): List<Entry> {
        return querySnapshot.map<QueryDocumentSnapshot, Entry> { queryDocumentSnapshot ->
            queryDocumentSnapshot.asEntry()
        }
    }

    fun map(documentSnapshot: DocumentSnapshot): Entry? {
        return documentSnapshot.asEntry()
    }

    private fun DocumentSnapshot.asEntry(): Entry {
        return Entry(
            this.id,
            this["headword"] as String,
            (this["category"] as Int).asCategory(),
            (this["senses"] as List<Map<String, Any>>).asSenses(),
            this["tags"] as List<String>
        )
    }

    private fun Int.asCategory(): Category {
        return when (this) {
            0 -> Category.NOUN
            1 -> Category.ADJECTIVE
            2 -> Category.VERB
            else -> Category.PHRASAL_VERB
        }
    }

    private fun List<Map<String, Any>>.asSenses(): List<Sense> {
        return this.map {
            val definition = it["definition"] as String
            val examples = it["examples"] as List<String>
            Sense(definition, examples)
        }
    }
}
