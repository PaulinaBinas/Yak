package com.binas.yak.ui.authentication.signup.presenter

import com.binas.yak.ui.authentication.signup.interactor.SignupInteractor
import com.binas.yak.ui.authentication.signup.view.SignupView
import com.binas.yak.ui.base.presenter.Presenter

interface SignupPresenter<V: SignupView, I: SignupInteractor>: Presenter<V, I> {

    fun setCurrentUser(email: String)
}