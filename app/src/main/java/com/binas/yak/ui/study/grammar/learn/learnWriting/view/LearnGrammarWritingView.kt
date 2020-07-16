package com.binas.yak.ui.study.grammar.learn.learnWriting.view

import com.binas.yak.ui.base.view.BaseView

interface LearnGrammarWritingView: BaseView {

    fun getTimeLeft(): Long

    fun getDuration(): Long
}