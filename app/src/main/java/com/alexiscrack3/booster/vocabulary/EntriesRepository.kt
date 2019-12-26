package com.alexiscrack3.booster.vocabulary

import com.alexiscrack3.booster.models.Entry
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Maybe
import io.reactivex.Single

class EntriesRepository(
    private val db: FirebaseFirestore,
    private val entriesMapper: EntriesMapper = EntriesMapper()
) {

    fun getEntries(): Single<List<Entry>> {
        return Single.create { emitter ->
            db.collection("entries")
                .get()
                .addOnSuccessListener { querySnapshot ->
                    val entries = entriesMapper.map(querySnapshot)
                    emitter.onSuccess(entries)
                }
                .addOnFailureListener { e ->
                    emitter.onError(e)
                }
        }
    }

    fun getEntry(id: String): Maybe<Entry> {
        return Maybe.create { emitter ->
            db.collection("entries")
                .document(id)
                .get()
                .addOnSuccessListener { documentSnapshot ->
                    val entry = entriesMapper.map(documentSnapshot)
                    if (entry == null) {
                        emitter.onComplete()
                    } else {
                        emitter.onSuccess(entry)
                    }
                }
                .addOnFailureListener { e ->
                    emitter.onError(e)
                }
        }
    }
}
