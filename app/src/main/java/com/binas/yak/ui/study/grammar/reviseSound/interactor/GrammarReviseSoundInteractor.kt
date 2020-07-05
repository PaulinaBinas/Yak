package com.binas.yak.ui.study.grammar.reviseSound.interactor

import com.binas.yak.data.model.grammar.Grammar
import com.binas.yak.data.model.grammar.GrammarRevisionFlashcard
import com.binas.yak.data.model.translation.Translation
import com.binas.yak.ui.base.interactor.Interactor

interface GrammarReviseSoundInteractor: Interactor {

    fun getGrammarRevisionFlashcard(id: Long): GrammarRevisionFlashcard

    fun getGrammar(id: Long): Grammar

    fun getTranslation(id: Long): Translation
}