package com.binas.yak.ui.study.common.incorrect.presenter

import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.study.common.incorrect.interactor.IncorrectInteractor
import com.binas.yak.ui.study.common.incorrect.view.IncorrectView
import javax.inject.Inject

class IncorrectPresenterImpl<V: IncorrectView, I: IncorrectInteractor>
@Inject internal constructor(interactor: I): BasePresenter<V, I>(interactor = interactor), IncorrectPresenter<V, I> {
}