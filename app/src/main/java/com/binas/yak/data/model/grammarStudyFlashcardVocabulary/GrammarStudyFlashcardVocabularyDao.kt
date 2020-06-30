package com.binas.yak.data.model.grammarStudyFlashcardVocabulary

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface GrammarStudyFlashcardVocabularyDao {

    @Query("SELECT * FROM grammarStudyFlashcard WHERE grammarStudyFlashcard.id = :flashcardId")
    fun getGrammarStudyFlashcardWithVocabulary(flashcardId: Long): LiveData<GrammarStudyFlashcardWithVocabulary>
}