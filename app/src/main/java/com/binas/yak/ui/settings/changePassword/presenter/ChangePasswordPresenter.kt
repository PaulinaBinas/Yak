package com.binas.yak.ui.settings.changePassword.presenter

import com.binas.yak.ui.base.presenter.Presenter
import com.binas.yak.ui.settings.changePassword.interactor.ChangePasswordInteractor
import com.binas.yak.ui.settings.changePassword.view.ChangePasswordView

interface ChangePasswordPresenter<V: ChangePasswordView, I: ChangePasswordInteractor>: Presenter<V, I> {
}