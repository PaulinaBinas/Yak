package com.binas.yak.ui.authentication.resetPassword.presenter

import com.binas.yak.ui.authentication.resetPassword.interactor.ResetPasswordInteractor
import com.binas.yak.ui.authentication.resetPassword.view.ResetPasswordView
import com.binas.yak.ui.base.presenter.BasePresenter
import javax.inject.Inject

class ResetPasswordPresenterImpl<V: ResetPasswordView, I: ResetPasswordInteractor>
@Inject internal constructor(interactor: I): BasePresenter<V, I>(interactor = interactor), ResetPasswordPresenter<V, I> {
}