package com.binas.yak.ui.authentication.login.presenter

import com.binas.yak.data.model.User
import com.binas.yak.ui.authentication.login.interactor.LoginInteractor
import com.binas.yak.ui.authentication.login.view.LoginView
import com.binas.yak.ui.base.presenter.BasePresenter
import javax.inject.Inject

class LoginPresenterImpl<V: LoginView, I: LoginInteractor> @Inject internal constructor(interactor: I): BasePresenter<V, I>(interactor = interactor), LoginPresenter<V, I>{

    override fun setCurrentUser(user: User) {
        interactor?.setCurrentUser(user)
    }
}