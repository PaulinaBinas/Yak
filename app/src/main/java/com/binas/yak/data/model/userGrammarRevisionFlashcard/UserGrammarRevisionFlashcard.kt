package com.binas.yak.data.model.userGrammarRevisionFlashcard

import androidx.room.Entity

@Entity(tableName = "User_GrammarRevisionFlashcard",primaryKeys = ["userId", "flashcardId"])
data class UserGrammarRevisionFlashcard (
    val userId: Long,
    val flashcardId: Long
)
