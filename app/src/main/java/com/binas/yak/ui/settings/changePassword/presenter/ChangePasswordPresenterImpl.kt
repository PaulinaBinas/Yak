package com.binas.yak.ui.settings.changePassword.presenter

import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.settings.changePassword.interactor.ChangePasswordInteractor
import com.binas.yak.ui.settings.changePassword.view.ChangePasswordView
import javax.inject.Inject

class ChangePasswordPresenterImpl<V: ChangePasswordView, I: ChangePasswordInteractor>
@Inject internal constructor(interactor: I): BasePresenter<V, I>(interactor = interactor), ChangePasswordPresenter<V, I> {
}