package com.binas.yak.data.model.userVocabularyStudyFlashcard

import androidx.room.Entity

@Entity(tableName = "User_VocabularyStudyFlashcard", primaryKeys = ["userId", "vocabularyStudyFlashcardId"])
data class UserVocabularyStudyFlashcard (
    val userId: Long,
    val vocabularyStudyFlashcardId: Long
)