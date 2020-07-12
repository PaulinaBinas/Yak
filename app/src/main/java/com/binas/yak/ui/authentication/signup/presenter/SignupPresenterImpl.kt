package com.binas.yak.ui.authentication.signup.presenter

import com.binas.yak.data.preferences.PreferenceHelper
import com.binas.yak.ui.authentication.signup.interactor.SignupInteractor
import com.binas.yak.ui.authentication.signup.view.SignupView
import com.binas.yak.ui.base.presenter.BasePresenter
import javax.inject.Inject

class SignupPresenterImpl<V: SignupView, I: SignupInteractor> @Inject internal constructor(interactor: I, var preferenceHelper: PreferenceHelper): BasePresenter<V, I>(interactor = interactor), SignupPresenter<V, I> {

    override fun setCurrentUser(email: String) {
        this.preferenceHelper.setCurrentUserEmail(email)
    }
}