package com.binas.yak.data.model.userVocabularyStudyFlashcard

import androidx.room.Entity

@Entity(primaryKeys = ["userId", "flashcardId"])
data class UserVocabularyStudyFlashcard (
    val userId: Long,
    val flashcardId: Long
)