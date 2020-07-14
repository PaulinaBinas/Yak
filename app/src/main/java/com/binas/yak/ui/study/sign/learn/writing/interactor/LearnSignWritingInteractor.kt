package com.binas.yak.ui.study.sign.learn.writing.interactor

import com.binas.yak.data.model.sign.SignRevisionFlashcard
import com.binas.yak.data.model.sign.SignStudyFlashcard
import com.binas.yak.data.model.studyDay.StudyDay
import com.binas.yak.ui.base.interactor.Interactor

interface LearnSignWritingInteractor: Interactor {

    fun scheduleReviewsOfSign(id: Long)

    fun markCardAsStudied(id: Long)

    fun getAllMatchingRevisionFlashcards(id: Long): List<SignRevisionFlashcard>

    fun saveStudyDay(day: StudyDay)

    fun getStudyDate(): StudyDay

    fun getSignStudyFlashcard(id: Long): SignStudyFlashcard

    fun getUserStudyTime(id: Long): Double

    fun setUserStudyTime(id: Long, time: Double)
}