package com.binas.yak.ui.study.sign.learn.writing.interactor

import com.binas.yak.data.model.sign.SignRepository
import com.binas.yak.data.model.sign.SignRevisionFlashcard
import java.time.LocalDate
import javax.inject.Inject

class LearnSignWritingInteractorImpl @Inject internal constructor(var signRepo: SignRepository): LearnSignWritingInteractor {

    override fun scheduleReviewsOfSign(id: Long) {
        var today = LocalDate.now().plusDays(1)
        signRepo.scheduleReviewsOfSign(id, today)
    }

    override fun markCardAsStudied(id: Long) {
        signRepo.markCardAsStudied(id)
    }

    override fun getAllMatchingRevisionFlashcards(id: Long): List<SignRevisionFlashcard> {
        return signRepo.getRevisionFlashcardsWithSignId(id)
    }
}