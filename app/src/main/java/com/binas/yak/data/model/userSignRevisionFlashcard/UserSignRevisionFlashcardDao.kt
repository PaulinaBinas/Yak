package com.binas.yak.data.model.userSignRevisionFlashcard

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface UserSignRevisionFlashcardDao {

    @Query("SELECT * FROM user WHERE user.id = :userId")
    fun getUserWithSignRevisionFlashcards(userId: Long): LiveData<UserWithSignRevisionFlashcard>
}