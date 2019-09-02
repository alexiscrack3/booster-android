package com.alexiscrack3.booster.vocabulary

import com.alexiscrack3.booster.models.Entry
import io.reactivex.Single

class VocabularyRepository(
    private val vocabularyService: VocabularyService,
    private val vocabularyMapper: VocabularyMapper = VocabularyMapper()
) {
    fun getEntries(): Single<List<Entry>> {
        return vocabularyService.getEntries().map { vocabularyMapper.map(it) }
    }
}
