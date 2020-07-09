package com.binas.yak.data.model.studyDay

import java.time.LocalDate
import javax.inject.Inject

class StudyDayRepositoryImpl @Inject internal constructor(var studyDayDao: StudyDayDao): StudyDayRepository {

    override fun updateStudyDay(day: StudyDay) {
        studyDayDao.updateStudyDay(day)
    }

    override fun getStudyDayByDate(date: LocalDate): StudyDay {
        return studyDayDao.getStudyDayByDate(date)
    }
}