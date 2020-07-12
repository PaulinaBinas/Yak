package com.binas.yak.ui.authentication.signup.interactor

import com.binas.yak.data.model.user.User
import com.binas.yak.ui.base.interactor.Interactor

interface SignupInteractor: Interactor {

    fun addUser(user: User)

    fun getUser(email: String): User
}