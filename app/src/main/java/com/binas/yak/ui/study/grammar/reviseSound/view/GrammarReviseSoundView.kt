package com.binas.yak.ui.study.grammar.reviseSound.view

import com.binas.yak.data.model.grammar.Grammar
import com.binas.yak.data.model.grammar.GrammarRevisionFlashcard
import com.binas.yak.data.model.translation.Translation
import com.binas.yak.ui.base.view.BaseView

interface GrammarReviseSoundView: BaseView {

    fun loadText()

    fun setContent(card: GrammarRevisionFlashcard, grammar: Grammar, text: Translation?)
}