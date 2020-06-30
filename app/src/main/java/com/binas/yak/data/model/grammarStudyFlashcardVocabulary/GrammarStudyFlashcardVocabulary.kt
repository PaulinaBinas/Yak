package com.binas.yak.data.model.grammarStudyFlashcardVocabulary

import androidx.room.Entity

@Entity(primaryKeys = ["flashcardId", "vocabularyid"])
data class GrammarStudyFlashcardVocabulary (
    val flashcardId: Long,
    val vocabularyId: Long
)