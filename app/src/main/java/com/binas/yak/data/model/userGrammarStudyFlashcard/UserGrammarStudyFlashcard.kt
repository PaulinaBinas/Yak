package com.binas.yak.data.model.userGrammarStudyFlashcard

import androidx.room.Entity

@Entity(tableName = "User_GrammarStudyFlashcard", primaryKeys = ["userId", "flashcardId"])
data class UserGrammarStudyFlashcard (
    val userId: Long,
    val flashcardId: Long
)