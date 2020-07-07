package com.binas.yak.data.model.studyOrder

import androidx.room.Dao
import androidx.room.Query

@Dao
interface StudyOrderDao {

    @Query("SELECT * FROM StudyOrder ORDER BY id")
    fun getStudyOrderList(): List<StudyOrder>
}