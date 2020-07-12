package com.binas.yak.data.model.user

interface UserRepository {

    fun addUser(user: User)

    fun getUserByEmail(email: String): User

    fun getUserById(id: Long): User
}