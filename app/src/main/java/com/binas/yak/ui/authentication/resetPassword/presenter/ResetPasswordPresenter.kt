package com.binas.yak.ui.authentication.resetPassword.presenter

import com.binas.yak.ui.authentication.resetPassword.interactor.ResetPasswordInteractor
import com.binas.yak.ui.authentication.resetPassword.view.ResetPasswordView
import com.binas.yak.ui.base.presenter.Presenter

interface ResetPasswordPresenter<V: ResetPasswordView, I: ResetPasswordInteractor>: Presenter<V, I> {
}