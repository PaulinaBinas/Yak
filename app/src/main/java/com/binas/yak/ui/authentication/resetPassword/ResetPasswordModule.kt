package com.binas.yak.ui.authentication.resetPassword

import com.binas.yak.ui.authentication.resetPassword.interactor.ResetPasswordInteractor
import com.binas.yak.ui.authentication.resetPassword.interactor.ResetPasswordInteractorImpl
import com.binas.yak.ui.authentication.resetPassword.presenter.ResetPasswordPresenter
import com.binas.yak.ui.authentication.resetPassword.presenter.ResetPasswordPresenterImpl
import com.binas.yak.ui.authentication.resetPassword.view.ResetPasswordView
import dagger.Module
import dagger.Provides

@Module
class ResetPasswordModule {

    @Provides
    internal fun provideResetPasswordInteractor(interactor: ResetPasswordInteractorImpl): ResetPasswordInteractor = interactor

    @Provides
    internal fun provideResetPasswordPresenter(presenter: ResetPasswordPresenterImpl<ResetPasswordView, ResetPasswordInteractor>)
            : ResetPasswordPresenter<ResetPasswordView, ResetPasswordInteractor> = presenter
}