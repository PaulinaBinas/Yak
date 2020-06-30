package com.binas.yak.data.model.userSignRevisionFlashcard

import androidx.room.Entity

@Entity(primaryKeys = ["userId", "flashcardId"])
data class UserSignRevisionFlashcard (
    val userId: Long,
    val flashcardId: Long
)