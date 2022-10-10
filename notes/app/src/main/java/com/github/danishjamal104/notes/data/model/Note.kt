package com.github.danishjamal104.notes.data.model

data class Note(
    var id: Int,
    var userId: String,
    var value: String,
    var timestamp: Long
)
