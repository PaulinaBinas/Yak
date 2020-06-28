package com.binas.yak.ui.main.presenter

import com.binas.yak.ui.base.presenter.Presenter
import com.binas.yak.ui.main.interactor.MainInteractor
import com.binas.yak.ui.main.view.MainView

interface MainPresenter<V: MainView, I: MainInteractor>: Presenter<V, I> {
}