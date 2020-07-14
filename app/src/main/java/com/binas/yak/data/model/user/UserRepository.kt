package com.binas.yak.data.model.user

interface UserRepository {

    fun addUser(user: User)

    fun getUserByEmail(email: String): User

    fun getUserById(id: Long): User

    fun setTotalMinutesStudiedByUserId(id: Long, minutes: Double)

    fun getTotalMinutesStudiedByUserId(id: Long): Double
}