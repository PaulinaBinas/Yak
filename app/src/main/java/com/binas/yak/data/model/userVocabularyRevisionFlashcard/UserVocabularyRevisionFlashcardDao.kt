package com.binas.yak.data.model.userVocabularyRevisionFlashcard

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface UserVocabularyRevisionFlashcardDao {

    @Query("SELECT * FROM user WHERE user.id = :userId")
    fun getUserWithVocabularyRevisionFlashcards(userId: Long): LiveData<UserWithVocabularyRevisionFlashcard>
}