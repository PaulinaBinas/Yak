package com.binas.yak.ui.study.sign.learn.writing.presenter

import com.binas.yak.ui.base.presenter.Presenter
import com.binas.yak.ui.study.sign.learn.writing.interactor.LearnSignWritingInteractor
import com.binas.yak.ui.study.sign.learn.writing.view.LearnSignWritingView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

interface LearnSignWritingPresenter<V: LearnSignWritingView, I: LearnSignWritingInteractor>: Presenter<V, I> {

    fun scheduleReviewCards(id: Long)

    fun markCardAsStudied(id: Long)
}