package com.binas.yak.ui.authentication.signup

import com.binas.yak.ui.authentication.signup.interactor.SignupInteractor
import com.binas.yak.ui.authentication.signup.interactor.SignupInteractorImpl
import com.binas.yak.ui.authentication.signup.presenter.SignupPresenter
import com.binas.yak.ui.authentication.signup.presenter.SignupPresenterImpl
import com.binas.yak.ui.authentication.signup.view.SignupView
import dagger.Module
import dagger.Provides

@Module
class SignupModule {

    @Provides
    internal fun provideSignupInteractorImpl() = SignupInteractorImpl()

    @Provides
    internal fun provideSignupInteractor(interactor: SignupInteractorImpl): SignupInteractor = interactor

    @Provides
    internal fun provideSignupPresenter(presenter: SignupPresenterImpl<SignupView, SignupInteractor>)
            : SignupPresenter<SignupView, SignupInteractor> = presenter
}