package com.binas.yak.data.model.userGrammarRevisionFlashcard

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface UserGrammarRevisionFlashcardDao {

    @Query("SELECT * FROM user WHERE user.id = :userId")
    fun getUserWithGrammarRevisionFlashcards(userId: Long): LiveData<UserWithGrammarRevisionFlashcard>
}