package com.binas.yak.ui.authentication.login

import com.binas.yak.ui.authentication.login.interactor.LoginInteractor
import com.binas.yak.ui.authentication.login.interactor.LoginInteractorImpl
import com.binas.yak.ui.authentication.login.presenter.LoginPresenter
import com.binas.yak.ui.authentication.login.presenter.LoginPresenterImpl
import com.binas.yak.ui.authentication.login.view.LoginView
import dagger.Module
import dagger.Provides

@Module
class LoginModule {

    @Provides
    internal fun provideLoginInteractor(interactor: LoginInteractorImpl): LoginInteractor = interactor

    @Provides
    internal fun provideLoginPresenter(presenter: LoginPresenterImpl<LoginView, LoginInteractor>): LoginPresenter<LoginView, LoginInteractor> = presenter
}