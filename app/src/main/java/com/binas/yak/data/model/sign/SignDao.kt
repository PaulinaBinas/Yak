package com.binas.yak.data.model.sign

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.binas.yak.data.model.RevisionType
import java.time.LocalDate

@Dao
interface SignDao {

    @Query("SELECT * FROM SignStudyFlashcard")
    fun getSignStudyFlashcards(): List<SignStudyFlashcard>

    @Query("SELECT * FROM SignStudyFlashcard WHERE SignStudyFlashcard.id = :id")
    fun getSignStudyFlashcardById(id: Long): SignStudyFlashcard

    @Query("SELECT * FROM SignRevisionFlashcard WHERE SignRevisionFlashcard.id = :id")
    fun getSignRevisionFlashcard(id: Long): SignRevisionFlashcard

    @Query("SELECT * FROM Sign WHERE Sign.id = :id")
    fun getSignByFlashcardId(id: Long): Sign

    @Insert
    fun addSignStudyFlashcards(flashcards: List<SignStudyFlashcard>)

    @Query("SELECT * FROM SignStudyFlashcard WHERE SignStudyFlashcard.ifStudied = 1")
    fun getAllStudiedSignFlashcards(): List<SignStudyFlashcard>

    @Query("SELECT * FROM SignRevisionFlashcard WHERE SignRevisionFlashcard.signId = :id")
    fun getSignRevisionFlashcardsWithSignId(id: Long): List<SignRevisionFlashcard>

    @Query("SELECT * FROM SignRevisionFlashcard WHERE SignRevisionFlashcard.nextDisplayTime <= :today")
    fun getScheduledSignRevisionFlashcards(today: LocalDate): List<SignRevisionFlashcard>
}