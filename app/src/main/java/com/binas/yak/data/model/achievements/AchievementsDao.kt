package com.binas.yak.data.model.achievements

import androidx.room.Dao
import androidx.room.Query

@Dao
interface AchievementsDao {

    @Query("SELECT COUNT(id) FROM SignStudyFlashcard")
    fun getCoundOfAllSigns(): Int

    @Query("SELECT COUNT(id) FROM SignStudyFlashcard WHERE SignStudyFlashcard.ifStudied = 1")
    fun getCountOfAllStudiedSigns(): Int

    @Query("SELECT COUNT(id) FROM VocabularyStudyFlashcard")
    fun getCountOfAllWords(): Int

    @Query("SELECT COUNT(id) FROM VocabularyStudyFlashcard WHERE VocabularyStudyFlashcard.ifStudied = 1")
    fun getCountOfAllStudiedWords(): Int

    @Query("SELECT COUNT(id) FROM GrammarStudyFlashcard")
    fun getCountOfAllGrammar(): Int

    @Query("SELECT COUNT(id) FROM GrammarStudyFlashcard WHERE GrammarStudyFlashcard.ifStudied = 1")
    fun getCountOfAllStudiedGrammar(): Int
}