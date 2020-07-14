package com.binas.yak.data.model.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    fun addUser(user: User)

    @Query("SELECT * FROM User WHERE User.id = :id")
    fun getUserById(id: Long): User

    @Query("SELECT * FROM User WHERE User.email = :email")
    fun getUserByEmail(email: String): User

    @Query("UPDATE User SET totalMinutesStudied = :minutes WHERE User.id = :id")
    fun setTotalMinutesStudiedByUserId(id: Long, minutes: Double)

    @Query("SELECT totalMinutesStudied FROM User WHERE User.id = :id")
    fun getTotalMinutesStudiedByUserId(id: Long): Double
}