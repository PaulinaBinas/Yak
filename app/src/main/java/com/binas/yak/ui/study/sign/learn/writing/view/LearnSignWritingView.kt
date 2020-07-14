package com.binas.yak.ui.study.sign.learn.writing.view

import com.binas.yak.ui.base.view.BaseView

interface LearnSignWritingView: BaseView {

    fun getTimeLeft(): Long

    fun getDuration(): Long
}