package com.binas.yak.data.model.userGrammarRevisionFlashcard

import androidx.room.Entity

@Entity(primaryKeys = ["userId", "flashcardId"])
data class UserGrammarRevisionFlashcard (
    val userId: Long,
    val flashcardId: Long
)
