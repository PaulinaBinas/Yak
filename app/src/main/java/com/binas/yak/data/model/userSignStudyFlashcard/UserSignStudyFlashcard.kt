package com.binas.yak.data.model.userSignStudyFlashcard

import androidx.room.Entity

@Entity(primaryKeys = ["userId", "flashcardId"])
data class UserSignStudyFlashcard (
    val userId: Long,
    val flashcardId: Long
)