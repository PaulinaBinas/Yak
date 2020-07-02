package com.binas.yak.ui.study.grammar.learn.studyCard.interactor

import com.binas.yak.data.model.grammar.Grammar
import com.binas.yak.data.model.grammar.GrammarStudyFlashcard
import com.binas.yak.data.model.translation.Translation
import com.binas.yak.ui.base.interactor.Interactor

interface GrammarStudyCardInteractor: Interactor {

    fun getGrammarStudyCard(id: Long): GrammarStudyFlashcard

    fun getGrammar(id: Long): Grammar

    fun getTranslation(id: Long): Translation
}