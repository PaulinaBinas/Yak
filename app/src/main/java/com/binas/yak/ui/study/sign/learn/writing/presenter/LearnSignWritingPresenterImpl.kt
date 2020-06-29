package com.binas.yak.ui.study.sign.learn.writing.presenter

import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.study.sign.learn.writing.interactor.LearnSignWritingInteractor
import com.binas.yak.ui.study.sign.learn.writing.view.LearnSignWritingView
import javax.inject.Inject

class LearnSignWritingPresenterImpl<V: LearnSignWritingView, I: LearnSignWritingInteractor>
@Inject internal constructor(interactor: I): BasePresenter<V, I>(interactor = interactor), LearnSignWritingPresenter<V, I> {
}