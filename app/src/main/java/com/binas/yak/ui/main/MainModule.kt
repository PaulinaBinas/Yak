package com.binas.yak.ui.main

import com.binas.yak.ui.main.interactor.MainInteractor
import com.binas.yak.ui.main.interactor.MainInteractorImpl
import com.binas.yak.ui.main.presenter.MainPresenter
import com.binas.yak.ui.main.presenter.MainPresenterImpl
import com.binas.yak.ui.main.view.MainView
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    internal fun provideMainInteractor(interactor: MainInteractorImpl): MainInteractor = interactor

    @Provides
    internal fun provideMainPresenter(presenter: MainPresenterImpl<MainView, MainInteractor>): MainPresenter<MainView, MainInteractor> = presenter
}