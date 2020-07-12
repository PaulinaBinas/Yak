package com.binas.yak.ui.authentication.login.interactor

import com.binas.yak.data.model.user.User
import com.binas.yak.data.model.user.UserRepository
import com.binas.yak.data.preferences.PreferenceHelper
import com.binas.yak.ui.base.interactor.BaseInteractor
import javax.inject.Inject

class LoginInteractorImpl @Inject internal constructor(var preferenceHelper: PreferenceHelper, var userRepo: UserRepository): LoginInteractor, BaseInteractor() {

    override fun setCurrentUser(email: String, id: Long) {
        preferenceHelper.setCurrentUserEmail(email)
        preferenceHelper.setCurrentUserId(id)
    }

    override fun getUser(email: String): User {
        return userRepo.getUserByEmail(email)
    }
}