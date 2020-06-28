package com.binas.yak.ui.main.splash.presenter

import com.binas.yak.ui.base.presenter.Presenter
import com.binas.yak.ui.main.splash.interactor.SplashInteractor
import com.binas.yak.ui.main.splash.view.SplashView

interface SplashPresenter<V: SplashView, I: SplashInteractor>: Presenter<V, I> {
}