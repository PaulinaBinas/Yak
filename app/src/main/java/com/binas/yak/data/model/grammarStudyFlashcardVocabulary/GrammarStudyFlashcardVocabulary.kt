package com.binas.yak.data.model.grammarStudyFlashcardVocabulary

import androidx.room.Entity

@Entity(tableName = "VocabularyListChoiceForGrammarFlashcard", primaryKeys = ["grammarStudyFlashcardId", "vocabularyId"])
data class GrammarStudyFlashcardVocabulary (
    val grammarStudyFlashcardId: Long,
    val vocabularyId: Long
)