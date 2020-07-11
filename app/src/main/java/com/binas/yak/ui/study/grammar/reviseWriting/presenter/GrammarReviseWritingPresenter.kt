package com.binas.yak.ui.study.grammar.reviseWriting.presenter

import com.binas.yak.ui.base.presenter.Presenter
import com.binas.yak.ui.study.grammar.reviseWriting.interactor.GrammarReviseWritingInteractor
import com.binas.yak.ui.study.grammar.reviseWriting.view.GrammarReviseWritingView

interface GrammarReviseWritingPresenter<V: GrammarReviseWritingView, I: GrammarReviseWritingInteractor>: Presenter<V, I> {

    fun start(id: Long)
}