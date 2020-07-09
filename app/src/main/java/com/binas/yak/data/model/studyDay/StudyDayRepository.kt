package com.binas.yak.data.model.studyDay

import java.time.LocalDate

interface StudyDayRepository {

    fun updateStudyDay(day: StudyDay)

    fun getStudyDayByDate(date: LocalDate): StudyDay
}