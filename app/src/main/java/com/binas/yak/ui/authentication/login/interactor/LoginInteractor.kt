package com.binas.yak.ui.authentication.login.interactor

import com.binas.yak.data.model.User
import com.binas.yak.ui.base.interactor.Interactor

interface LoginInteractor: Interactor {

    fun setCurrentUser(user: User)
}