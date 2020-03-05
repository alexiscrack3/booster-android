package com.alexiscrack3.booster.entries

import com.alexiscrack3.booster.models.Category
import com.alexiscrack3.booster.models.Entry
import com.alexiscrack3.booster.models.Sense
import com.google.firebase.firestore.DocumentSnapshot

class EntriesMapper {

    fun map(querySnapshot: Iterable<DocumentSnapshot>): List<Entry> {
        return querySnapshot.map { queryDocumentSnapshot ->
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
            (this["category"] as Long).asCategory(),
            (this["senses"] as List<Map<String, Any>>).asSenses(),
            this["tags"] as List<String>? ?: emptyList()
        )
    }

    private fun Long.asCategory(): Category {
        return when (this) {
            0L -> Category.NOUN
            1L -> Category.ADJECTIVE
            2L -> Category.VERB
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
