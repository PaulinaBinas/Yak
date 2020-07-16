package com.binas.yak.ui.study.vocabulary.learn.learnWriting.view

import com.binas.yak.ui.base.view.BaseView

interface LearnVocabularyWritingView: BaseView {

    fun getTimeLeft(): Long

    fun getDuration(): Long
}