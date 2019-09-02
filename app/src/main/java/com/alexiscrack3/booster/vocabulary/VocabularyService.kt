package com.alexiscrack3.booster.vocabulary

import com.alexiscrack3.booster.models.Entry
import com.alexiscrack3.booster.models.Response
import io.reactivex.Single
import retrofit2.http.GET

interface VocabularyService {

    @GET("entries")
    fun getEntries(): Single<Response<List<Entry>>>
}
