package com.binas.yak.data.model.userSignRevisionFlashcard

import androidx.room.Entity

@Entity(tableName ="User_SignRevisionFlashcard", primaryKeys = ["userId", "signRevisionFlashcardId"])
data class UserSignRevisionFlashcard (
    val userId: Long,
    val signRevisionFlashcardId: Long
)