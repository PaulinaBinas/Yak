package com.binas.yak.ui.authentication.login.interactor

import com.binas.yak.data.model.User
import com.binas.yak.data.preferences.PreferenceHelper
import com.binas.yak.ui.base.interactor.BaseInteractor
import javax.inject.Inject

class LoginInteractorImpl @Inject internal constructor(var preferenceHelper: PreferenceHelper): LoginInteractor, BaseInteractor() {

    override fun setCurrentUser(user: User) {
        preferenceHelper.setCurrentUserEmail(user.email)
    }
}