package com.binas.yak.data.model.sign

import java.time.LocalDate
import javax.inject.Inject


class SignRepositoryImpl @Inject internal constructor(var signDao: SignDao): SignRepository {

    override fun getSignStudyFlashcards() = signDao.getSignStudyFlashcards()

    override fun getSignStudyFlashcardById(id: Long): SignStudyFlashcard {
        return signDao.getSignStudyFlashcardById(id)
    }

    override fun getSignRevisionFlashcardById(id: Long): SignRevisionFlashcard {
        return signDao.getSignRevisionFlashcard(id)
    }

    override fun getSignByFlashcardId(id: Long): Sign {
        return signDao.getSignByFlashcardId(id)
    }

    override fun addSignStudyFlashcards(flashcards: List<SignStudyFlashcard>) {
        signDao.addSignStudyFlashcards(flashcards)
    }

    override fun getAllStudiedCards(): List<SignStudyFlashcard> {
       return signDao.getAllStudiedSignFlashcards()
    }

    override fun getRevisionFlashcardsWithSignId(id: Long): List<SignRevisionFlashcard> {
        return signDao.getSignRevisionFlashcardsWithSignId(id)
    }

    override fun getScheduledRevisionFlashcards(today: LocalDate): List<SignRevisionFlashcard> {
        return signDao.getScheduledSignRevisionFlashcards(today, LocalDate.of(2020,1,1))
    }

    override fun markCardAsStudied(id: Long) {
        signDao.markCardAsStudied(id)
    }

    override fun scheduleReviewsOfSign(id: Long, date: LocalDate) {
        signDao.scheduleReviewsOfSign(id, date)
    }

    override fun saveSignRevisionFlashcard(card: SignRevisionFlashcard) {
        signDao.saveSignRevisionFlashcard(card)
    }
}