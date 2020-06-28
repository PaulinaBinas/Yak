package com.binas.yak.ui.authentication.authMenu

import com.binas.yak.ui.authentication.authMenu.interactor.AuthMenuInteractor
import com.binas.yak.ui.authentication.authMenu.interactor.AuthMenuInteractorImpl
import com.binas.yak.ui.authentication.authMenu.presenter.AuthMenuPresenter
import com.binas.yak.ui.authentication.authMenu.presenter.AuthMenuPresenterImpl
import com.binas.yak.ui.authentication.authMenu.view.AuthMenuView
import dagger.Module
import dagger.Provides

@Module
class AuthMenuModule {

    @Provides
    internal fun provideAuthMenuInteractor(interactor: AuthMenuInteractorImpl): AuthMenuInteractor = interactor

    @Provides
    internal fun prpvodeAuthMenuPresenter(presenter: AuthMenuPresenterImpl<AuthMenuView, AuthMenuInteractor>): AuthMenuPresenter<AuthMenuView, AuthMenuInteractor> = presenter
}