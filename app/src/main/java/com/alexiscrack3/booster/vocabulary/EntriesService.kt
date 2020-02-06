package com.alexiscrack3.booster.vocabulary

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import io.reactivex.Maybe
import io.reactivex.Single

class EntriesService(
    private val firestore: FirebaseFirestore
) {
    companion object {
        const val ENTRIES_PATH = "entries"
    }

    fun getEntries(): Single<QuerySnapshot> {
        return Single.create { emitter ->
            firestore.collection(ENTRIES_PATH)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    emitter.onSuccess(querySnapshot)
                }
                .addOnFailureListener { e ->
                    emitter.onError(e)
                }
        }
    }

    fun getEntry(id: String): Maybe<DocumentSnapshot> {
        return Maybe.create { emitter ->
            firestore.collection(ENTRIES_PATH)
                .document(id)
                .get()
                .addOnSuccessListener { documentSnapshot ->
                    if (documentSnapshot == null) {
                        emitter.onComplete()
                    } else {
                        emitter.onSuccess(documentSnapshot)
                    }
                }
                .addOnFailureListener { e ->
                    emitter.onError(e)
                }
        }
    }
}
