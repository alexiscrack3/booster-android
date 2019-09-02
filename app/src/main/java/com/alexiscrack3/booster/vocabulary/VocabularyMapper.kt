package com.alexiscrack3.booster.vocabulary

import com.alexiscrack3.booster.models.Entry
import com.alexiscrack3.booster.models.Response

class VocabularyMapper {

    fun map(response: Response<List<Entry>>): List<Entry> {
        return response.data
    }
}
