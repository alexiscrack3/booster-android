package com.alexiscrack3.booster.vocabulary

import com.alexiscrack3.booster.models.Entry
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Single

class VocabularyRepository(
    private val db: FirebaseFirestore,
    private val vocabularyMapper: VocabularyMapper = VocabularyMapper()
) {

    fun getEntries(): Single<List<Entry>> {
        return Single.create { emitter ->
            db.collection("entries")
                .get()
                .addOnSuccessListener { result ->
                    val entries = vocabularyMapper.map(result)
                    emitter.onSuccess(entries)
                }
                .addOnFailureListener { e ->
                    emitter.onError(e)
                }
        }
    }
}
