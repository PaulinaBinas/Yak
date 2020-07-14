package com.binas.yak.ui.study.sign.learn.writing.interactor

import com.binas.yak.data.model.sign.SignRepository
import com.binas.yak.data.model.sign.SignRevisionFlashcard
import com.binas.yak.data.model.sign.SignStudyFlashcard
import com.binas.yak.data.model.studyDay.StudyDay
import com.binas.yak.data.model.studyDay.StudyDayRepository
import com.binas.yak.data.model.user.UserRepository
import java.time.LocalDate
import javax.inject.Inject

class LearnSignWritingInteractorImpl @Inject internal constructor(var signRepo: SignRepository, var studyDayRepo: StudyDayRepository, var userRepo: UserRepository): LearnSignWritingInteractor {

    override fun scheduleReviewsOfSign(id: Long) {
        var today = LocalDate.now()
        signRepo.scheduleReviewsOfSign(id, today)
    }

    override fun markCardAsStudied(id: Long) {
        signRepo.markCardAsStudied(id)
    }

    override fun getAllMatchingRevisionFlashcards(id: Long): List<SignRevisionFlashcard> {
        return signRepo.getRevisionFlashcardsWithSignId(id)
    }

    override fun saveStudyDay(day: StudyDay) {
        studyDayRepo.updateStudyDay(day)
    }

    override fun getStudyDate(): StudyDay {
        var today = LocalDate.now()
        return studyDayRepo.getStudyDayByDate(today)
    }

    override fun getSignStudyFlashcard(id: Long): SignStudyFlashcard {
        return signRepo.getSignStudyFlashcardById(id)
    }

    override fun getUserStudyTime(id: Long): Double {
        return userRepo.getTotalMinutesStudiedByUserId(id)
    }

    override fun setUserStudyTime(id: Long, time: Double) {
        userRepo.setTotalMinutesStudiedByUserId(id, time)
    }
}