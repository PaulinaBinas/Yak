package com.binas.yak.ui.main.presenter

import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.main.interactor.MainInteractor
import com.binas.yak.ui.main.view.MainView
import javax.inject.Inject

class MainPresenterImpl<V: MainView, I: MainInteractor> @Inject internal constructor(interactor: I): BasePresenter<V, I>(interactor = interactor), MainPresenter<V, I> {
}