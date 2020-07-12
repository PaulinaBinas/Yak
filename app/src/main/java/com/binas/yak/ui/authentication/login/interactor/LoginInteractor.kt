package com.binas.yak.ui.authentication.login.interactor

import com.binas.yak.data.model.user.User
import com.binas.yak.ui.base.interactor.Interactor

interface LoginInteractor: Interactor {

    fun setCurrentUser(email: String, id: Long)

    fun getUser(email: String): User
}