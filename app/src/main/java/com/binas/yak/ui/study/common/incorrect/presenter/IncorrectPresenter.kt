package com.binas.yak.ui.study.common.incorrect.presenter

import com.binas.yak.ui.base.presenter.Presenter
import com.binas.yak.ui.study.common.incorrect.interactor.IncorrectInteractor
import com.binas.yak.ui.study.common.incorrect.view.IncorrectView

interface IncorrectPresenter<V: IncorrectView, I: IncorrectInteractor>: Presenter<V, I> {
}