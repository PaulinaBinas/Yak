package com.binas.yak.ui.settings.changePassword

import com.binas.yak.ui.settings.changePassword.interactor.ChangePasswordInteractor
import com.binas.yak.ui.settings.changePassword.interactor.ChangePasswordInteractorImpl
import com.binas.yak.ui.settings.changePassword.presenter.ChangePasswordPresenter
import com.binas.yak.ui.settings.changePassword.presenter.ChangePasswordPresenterImpl
import com.binas.yak.ui.settings.changePassword.view.ChangePasswordView
import dagger.Module
import dagger.Provides

@Module
class ChangePasswordModule {

    @Provides
    internal fun provideChangePasswordInteractor(interactor: ChangePasswordInteractorImpl): ChangePasswordInteractor = interactor

    @Provides
    internal fun provideChangePasswordPresenter(presenter: ChangePasswordPresenterImpl<ChangePasswordView, ChangePasswordInteractor>)
            : ChangePasswordPresenter<ChangePasswordView, ChangePasswordInteractor> = presenter
}