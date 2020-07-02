package com.binas.yak.data.model.grammar

interface GrammarRepository {

    fun getGrammar(id: Long): Grammar

    fun getGrammarStudyCard(id: Long): GrammarStudyFlashcard

    fun getGrammarRevisionCard(id: Long): GrammarRevisionFlashcard
}