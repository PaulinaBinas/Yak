package com.binas.yak.ui.study.grammar.reviseWriting.interactor

import com.binas.yak.data.model.grammar.Grammar
import com.binas.yak.data.model.grammar.GrammarRevisionFlashcard
import com.binas.yak.ui.base.interactor.Interactor

interface GrammarReviseWritingInteractor: Interactor {

    fun getGrammarRevisionFlashcard(id: Long): GrammarRevisionFlashcard

    fun getGrammar(id: Long): Grammar
}