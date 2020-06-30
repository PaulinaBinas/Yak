package com.binas.yak.data.model.userVocabularyStudyFlashcard

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface UserVocabularyStudyFlashcardDao {

    @Query("SELECT * FROM user WHERE user.id = :userId")
    fun getUserWithVocabularyStudyFlashcards(userId: Long): LiveData<UserWithVocabularyStudyFlashcard>
}