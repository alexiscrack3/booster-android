package com.alexiscrack3.booster.models

data class Entry(
    val id: String,
    val headword: String,
    val category: Category,
    val senses: List<Sense>,
    val tags: List<String>
)
