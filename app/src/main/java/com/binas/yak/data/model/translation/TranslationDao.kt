package com.binas.yak.data.model.translation

import androidx.room.Dao
import androidx.room.Query

@Dao
interface TranslationDao {

    @Query("SELECT * FROM Translation WHERE Translation.id = :id")
    fun getTranslationById(id: Long): Translation
}