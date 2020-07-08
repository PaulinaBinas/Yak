package com.binas.yak.ui.study.grammar.learn.learnWriting.interactor

import com.binas.yak.data.model.grammar.GrammarRevisionFlashcard
import com.binas.yak.ui.base.interactor.Interactor

interface LearnGrammarWritingInteractor: Interactor {

    fun scheduleReviewsOfGrammar(id: Long)

    fun markCardAsStudied(id: Long)

    fun getAllMatchingRevisionFlashcards(id: Long): List<GrammarRevisionFlashcard>
}