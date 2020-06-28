package com.binas.yak.ui.authentication.signup.presenter

import com.binas.yak.ui.authentication.signup.interactor.SignupInteractor
import com.binas.yak.ui.authentication.signup.view.SignupView
import com.binas.yak.ui.base.presenter.BasePresenter
import javax.inject.Inject

class SignupPresenterImpl<V: SignupView, I: SignupInteractor> @Inject internal constructor(interactor: I): BasePresenter<V, I>(interactor = interactor), SignupPresenter<V, I> {
}