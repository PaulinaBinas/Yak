package com.binas.yak.data.model.grammar

import java.time.LocalDate

interface GrammarRepository {

    fun getGrammar(id: Long): Grammar

    fun getGrammarStudyCard(id: Long): GrammarStudyFlashcard

    fun getGrammarRevisionCard(id: Long): GrammarRevisionFlashcard

    fun getAllStudiedGrammarStudyFlashcards(): List<GrammarStudyFlashcard>

    fun getRevisionFlashcardsWithGrammarId(id: Long): List<GrammarRevisionFlashcard>

    fun getScheduledRevisionFlashcards(today: LocalDate): List<GrammarRevisionFlashcard>
}