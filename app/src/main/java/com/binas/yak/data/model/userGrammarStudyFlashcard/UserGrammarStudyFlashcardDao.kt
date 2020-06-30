package com.binas.yak.data.model.userGrammarStudyFlashcard

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface UserGrammarStudyFlashcardDao {

    @Query("SELECT * FROM user WHERE user.id = :userId")
    fun getUserWithGrammarStudyFlashcards(userId: Long): LiveData<UserWithGrammarStudyFlashcard>
}