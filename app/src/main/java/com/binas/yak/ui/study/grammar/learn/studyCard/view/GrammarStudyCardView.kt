package com.binas.yak.ui.study.grammar.learn.studyCard.view

import com.binas.yak.data.model.grammar.Grammar
import com.binas.yak.data.model.grammar.GrammarStudyFlashcard
import com.binas.yak.data.model.translation.Translation
import com.binas.yak.ui.base.view.BaseView

interface GrammarStudyCardView: BaseView {

    fun setContent(card: GrammarStudyFlashcard, grammar: Grammar, text: Translation?)

    fun clickPlaySound()
}