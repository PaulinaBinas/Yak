package com.binas.yak.data.model.userSignStudyFlashcard

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface UserSignStudyFlashcardDao {

    @Query("SELECT * FROM user WHERE user.id = :userId")
    fun getUserWithSignStudyFlashcards(userId: Long): LiveData<UserWithSighStudyFlashcard>
}