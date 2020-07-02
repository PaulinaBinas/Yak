package com.binas.yak.data.model.grammarStudyFlashcardVocabulary

import androidx.room.Entity
import androidx.room.ForeignKey
import com.binas.yak.data.model.GrammarStudyFlashcard
import com.binas.yak.data.model.Vocabulary

@Entity(tableName = "VocabularyListChoiceForGrammarFlashcard", primaryKeys = ["grammarStudyFlashcardId", "vocabularyId"],
    foreignKeys = [(ForeignKey(entity = GrammarStudyFlashcard::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("grammarStudyFlashcardId"))), ForeignKey(entity = Vocabulary::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("vocabularyId"))])
data class GrammarStudyFlashcardVocabulary (
    val grammarStudyFlashcardId: Long,
    val vocabularyId: Long
)