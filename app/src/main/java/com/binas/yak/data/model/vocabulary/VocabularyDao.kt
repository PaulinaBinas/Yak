package com.binas.yak.data.model.vocabulary

import androidx.room.Dao
import androidx.room.Query

@Dao
interface VocabularyDao {

    @Query("SELECT * FROM Vocabulary WHERE Vocabulary.id = :id")
    fun getVocabularyById(id: Long): Vocabulary

    @Query("SELECT * FROM VocabularyStudyFlashcard WHERE VocabularyStudyFlashcard.id = :id")
    fun getStudyFlashcardById(id: Long): VocabularyStudyFlashcard

    @Query("SELECT * FROM VocabularyRevisionFlashcard WHERE VocabularyRevisionFlashcard.id = :id")
    fun getRevisionFlashcardById(id: Long): VocabularyRevisionFlashcard

    @Query("SELECT * FROM VocabularyStudyFlashcard WHERE VocabularyStudyFlashcard.ifStudied = 1")
    fun getAllStudiedVocabularyFlashcards(): List<VocabularyStudyFlashcard>
}