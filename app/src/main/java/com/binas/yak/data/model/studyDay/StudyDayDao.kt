package com.binas.yak.data.model.studyDay

import androidx.room.*
import java.time.LocalDate

@Dao
interface StudyDayDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateStudyDay(day: StudyDay)

    @Query("SELECT * FROM StudyDay WHERE StudyDay.date = :date")
    fun getStudyDayByDate(date: LocalDate): StudyDay
}