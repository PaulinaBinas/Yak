package com.binas.yak.ui.authentication.login.presenter

import com.binas.yak.data.model.user.User
import com.binas.yak.ui.authentication.login.interactor.LoginInteractor
import com.binas.yak.ui.authentication.login.view.LoginView
import com.binas.yak.ui.base.presenter.BasePresenter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginPresenterImpl<V: LoginView, I: LoginInteractor> @Inject internal constructor(interactor: I): BasePresenter<V, I>(interactor = interactor), LoginPresenter<V, I>{

    override fun setCurrentUser(email: String) {
        GlobalScope.launch {
            var dbUser = interactor?.getUser(email)
            if (dbUser != null) {
                dbUser.id?.let { interactor?.setCurrentUser(email, it) }
            }
        }
    }
}