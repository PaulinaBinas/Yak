package com.binas.yak.data.model.grammar

import androidx.room.Dao
import androidx.room.Query
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
}