package com.binas.yak.ui.study.grammar.learn.learnWriting.presenter

import com.binas.yak.ui.base.presenter.Presenter
import com.binas.yak.ui.study.grammar.learn.learnWriting.interactor.LearnGrammarWritingInteractor
import com.binas.yak.ui.study.grammar.learn.learnWriting.view.LearnGrammarWritingView

interface LearnGrammarWritingPresenter<V: LearnGrammarWritingView, I: LearnGrammarWritingInteractor>: Presenter<V, I> {

    fun scheduleReviewCards(id: Long)

    fun markCardAsStudied(id: Long)
}