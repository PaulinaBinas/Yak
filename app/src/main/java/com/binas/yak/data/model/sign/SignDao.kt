package com.binas.yak.data.model.sign

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SignDao {

    @Query("SELECT * FROM SignStudyFlashcard")
    fun getSignStudyFlashcards(): LiveData<List<SignStudyFlashcard>>

    @Insert
    fun addSignStudyFlashcards(flashcards: List<SignStudyFlashcard>)
}