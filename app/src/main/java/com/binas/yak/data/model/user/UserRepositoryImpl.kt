package com.binas.yak.data.model.user

import javax.inject.Inject

class UserRepositoryImpl @Inject internal constructor(var userDao: UserDao): UserRepository {

    override fun addUser(user: User) {
        userDao.addUser(user)
    }

    override fun getUserByEmail(email: String): User {
        return userDao.getUserByEmail(email)
    }

    override fun getUserById(id: Long): User {
        return userDao.getUserById(id)
    }
}