package com.binas.yak.data.model.sign

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SignDao {

    @Query("SELECT * FROM SignStudyFlashcard")
    fun getSignStudyFlashcards(): List<SignStudyFlashcard>

    @Query("SELECT * FROM SignStudyFlashcard WHERE SignStudyFlashcard.id = :id")
    fun getSignStudyFlashcardById(id: Long): SignStudyFlashcard

    @Query("SELECT * FROM Sign WHERE Sign.id = :id")
    fun getSignByFlashcardId(id: Long): Sign

    @Insert
    fun addSignStudyFlashcards(flashcards: List<SignStudyFlashcard>)
}