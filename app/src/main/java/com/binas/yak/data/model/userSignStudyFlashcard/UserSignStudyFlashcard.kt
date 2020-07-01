package com.binas.yak.data.model.userSignStudyFlashcard

import androidx.room.Entity

@Entity(tableName = "User_SignStudyFlashcard", primaryKeys = ["userId", "signStudyFlashcardId"])
data class UserSignStudyFlashcard (
    val userId: Long,
    val signStudyFlashcardId: Long
)