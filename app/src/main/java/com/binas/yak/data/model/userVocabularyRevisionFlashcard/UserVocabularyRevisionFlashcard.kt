package com.binas.yak.data.model.userVocabularyRevisionFlashcard

import androidx.room.Entity

@Entity(tableName = "User_VocabularyRevisionFlashcard", primaryKeys = ["userId", "vocabularyRevisionFlashcardId"])
data class UserVocabularyRevisionFlashcard (
    val userId: Long,
    val vocabularyRevisionFlashcardId: Long
)