package com.binas.yak.data.model.grammar

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import java.time.LocalDate

@Dao
interface GrammarDao {

    @Query("SELECT * FROM Grammar WHERE Grammar.id = :id")
    fun getGrammarById(id: Long): Grammar

    @Query("SELECT * FROM GrammarStudyFlashcard WHERE GrammarStudyFlashcard.id = :id")
    fun getGrammarStudyFlashcardById(id: Long): GrammarStudyFlashcard

    @Query("SELECT * FROM GrammarRevisionFlashcard WHERE GrammarRevisionFlashcard.id = :id")
    fun getGrammarRevisionFlashcardById(id: Long): GrammarRevisionFlashcard

    @Query("SELECT * FROM GrammarStudyFlashcard WHERE GrammarStudyFlashcard.ifStudied = 1")
    fun getAllStudiedGrammarStudyFlashcards(): List<GrammarStudyFlashcard>

    @Query("SELECT * FROM GrammarRevisionFlashcard WHERE GrammarRevisionFlashcard.grammarId = :id")
    fun getGrammarRevisionFlashcardsWithGrammarId(id: Long): List<GrammarRevisionFlashcard>

    @Query("SELECT * FROM GrammarRevisionFlashcard WHERE GrammarRevisionFlashcard.nextDisplayTime <= :today")
    fun getScheduledGrammarRevisionFlashcards(today: LocalDate): List<GrammarRevisionFlashcard>

    @Query("UPDATE GrammarStudyFlashcard SET ifStudied = 1 WHERE GrammarStudyFlashcard.grammarId = :id")
    fun markCardWithMatchingGrammarIdAsStudied(id: Long)

    @Query("UPDATE GrammarRevisionFlashcard SET nextDisplayTime = :date WHERE grammarId = :id")
    fun scheduleReviewsOfGrammar(id: Long, date: LocalDate)
}