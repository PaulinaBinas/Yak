package com.binas.yak.data.model.userVocabularyRevisionFlashcard

import androidx.room.Entity

@Entity(primaryKeys = ["userId", "flashcardId"])
data class UserVocabularyRevisionFlashcard (
    val userId: Long,
    val flashcardId: Long
)