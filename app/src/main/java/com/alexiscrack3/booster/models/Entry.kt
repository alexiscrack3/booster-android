package com.alexiscrack3.booster.models

data class Entry(
    val id: String,
    val headword: String,
    val `class`: String,
    val pronunciation: String?,
    val definitions: List<String>
)
