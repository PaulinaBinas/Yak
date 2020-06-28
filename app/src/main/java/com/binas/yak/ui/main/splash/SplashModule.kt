package com.binas.yak.ui.main.splash

import com.binas.yak.ui.main.splash.interactor.SplashInteractor
import com.binas.yak.ui.main.splash.interactor.SplashInteractorImpl
import com.binas.yak.ui.main.splash.presenter.SplashPresenter
import com.binas.yak.ui.main.splash.presenter.SplashPresenterImpl
import com.binas.yak.ui.main.splash.view.SplashView
import dagger.Module
import dagger.Provides

@Module
class SplashModule {

    @Provides
    internal fun provideSplashInteractor(interactor: SplashInteractorImpl): SplashInteractor = interactor

    @Provides
    internal fun provideSplashPresenter(presenter: SplashPresenterImpl<SplashView, SplashInteractor>): SplashPresenter<SplashView, SplashInteractor> = presenter
}