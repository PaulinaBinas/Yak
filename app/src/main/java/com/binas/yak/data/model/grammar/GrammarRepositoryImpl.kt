package com.binas.yak.data.model.grammar

import java.time.LocalDate
import javax.inject.Inject

class GrammarRepositoryImpl @Inject internal constructor(var grammarDao: GrammarDao): GrammarRepository {

    override fun getGrammar(id: Long): Grammar {
        return grammarDao.getGrammarById(id)
    }

    override fun getGrammarStudyCard(id: Long): GrammarStudyFlashcard {
        return grammarDao.getGrammarStudyFlashcardById(id)
    }

    override fun getGrammarRevisionCard(id: Long): GrammarRevisionFlashcard {
        return grammarDao.getGrammarRevisionFlashcardById(id)
    }

    override fun getAllStudiedGrammarStudyFlashcards(): List<GrammarStudyFlashcard> {
        return grammarDao.getAllStudiedGrammarStudyFlashcards()
    }

    override fun getRevisionFlashcardsWithGrammarId(id: Long): List<GrammarRevisionFlashcard> {
        return grammarDao.getGrammarRevisionFlashcardsWithGrammarId(id)
    }

    override fun getScheduledRevisionFlashcards(today: LocalDate): List<GrammarRevisionFlashcard> {
        return grammarDao.getScheduledGrammarRevisionFlashcards(today)
    }
}