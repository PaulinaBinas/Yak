package com.binas.yak.ui.study.common.correct.presenter

import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.study.common.correct.interactor.CorrectInteractor
import com.binas.yak.ui.study.common.correct.view.CorrectView
import javax.inject.Inject

class CorrectPresenterImpl<V: CorrectView, I: CorrectInteractor>
@Inject internal constructor(interactor: I):BasePresenter<V, I>(interactor = interactor), CorrectPresenter<V, I> {
}