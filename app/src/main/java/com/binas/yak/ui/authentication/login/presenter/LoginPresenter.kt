package com.binas.yak.ui.authentication.login.presenter

import com.binas.yak.data.model.user.User
import com.binas.yak.ui.authentication.login.interactor.LoginInteractor
import com.binas.yak.ui.authentication.login.view.LoginView
import com.binas.yak.ui.base.presenter.Presenter

interface LoginPresenter<V: LoginView, I: LoginInteractor>: Presenter<V, I> {

    fun setCurrentUser(email: String)
}