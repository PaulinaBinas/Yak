package com.binas.yak.ui.study.grammar.reviseWriting.view

import com.binas.yak.data.model.grammar.Grammar
import com.binas.yak.data.model.grammar.GrammarRevisionFlashcard
import com.binas.yak.ui.base.view.BaseView

interface GrammarReviseWritingView: BaseView {

    fun setContent(card: GrammarRevisionFlashcard, grammar: Grammar)

    fun clickSoundButton()
}