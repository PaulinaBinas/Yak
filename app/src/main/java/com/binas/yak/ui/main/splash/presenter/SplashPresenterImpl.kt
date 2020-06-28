package com.binas.yak.ui.main.splash.presenter

import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.main.splash.interactor.SplashInteractor
import com.binas.yak.ui.main.splash.view.SplashView
import javax.inject.Inject

class SplashPresenterImpl<V: SplashView, I: SplashInteractor>
@Inject internal constructor(interactor: I): BasePresenter<V, I>(interactor = interactor), SplashPresenter<V, I>{
}