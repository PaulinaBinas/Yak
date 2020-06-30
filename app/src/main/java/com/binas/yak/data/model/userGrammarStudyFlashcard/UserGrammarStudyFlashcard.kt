package com.binas.yak.data.model.userGrammarStudyFlashcard

import androidx.room.Entity

@Entity(primaryKeys = ["userId", "flashcardId"])
data class UserGrammarStudyFlashcard (
    val userId: Long,
    val flashcardId: Long
)