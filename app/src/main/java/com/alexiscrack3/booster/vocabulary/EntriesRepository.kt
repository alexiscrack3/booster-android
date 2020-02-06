package com.alexiscrack3.booster.vocabulary

import com.alexiscrack3.booster.models.Entry
import io.reactivex.Maybe
import io.reactivex.Single

class EntriesRepository(
    private val entriesService: EntriesService,
    private val entriesMapper: EntriesMapper = EntriesMapper()
) {

    fun getEntries(): Single<List<Entry>> {
        return entriesService.getEntries()
            .map { entriesMapper.map(it) }
    }

    fun getEntry(id: String): Maybe<Entry> {
        return entriesService.getEntry(id)
            .map { entriesMapper.map(it) }
    }
}
