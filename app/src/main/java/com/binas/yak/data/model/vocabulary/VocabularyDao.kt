package com.binas.yak.data.model.vocabulary

import androidx.room.Dao
import androidx.room.Query
import java.time.LocalDate

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

    @Query("SELECT * FROM VocabularyRevisionFlashcard WHERE VocabularyRevisionFlashcard.vocabularyId = :id")
    fun getVocabularyRevisionFlashcardsWithVocabularyId(id: Long): List<VocabularyRevisionFlashcard>

    @Query("SELECT * FROM VocabularyRevisionFlashcard WHERE VocabularyRevisionFlashcard.nextDisplayTime BETWEEN :past AND :today")
    fun getScheduledGrammarRevisionFlashcards(today: LocalDate, past: LocalDate): List<VocabularyRevisionFlashcard>

    @Query("UPDATE VocabularyStudyFlashcard SET ifStudied = 1 WHERE VocabularyStudyFlashcard.vocabularyId = :id")
    fun markCardAsStudied(id: Long)

    @Query("UPDATE VocabularyRevisionFlashcard SET nextDisplayTime = :date WHERE VocabularyRevisionFlashcard.vocabularyId = :id")
    fun scheduleReviewsOfVocabulary(id: Long, date: LocalDate)
}