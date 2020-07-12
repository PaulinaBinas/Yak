package com.binas.yak.ui.authentication.signup.interactor

import com.binas.yak.data.model.user.User
import com.binas.yak.data.model.user.UserRepository
import javax.inject.Inject

class SignupInteractorImpl @Inject internal constructor(var userRepo: UserRepository): SignupInteractor {

    override fun addUser(user: User) {
        userRepo.addUser(user)
    }

    override fun getUser(email: String): User {
        return userRepo.getUserByEmail(email)
    }
}