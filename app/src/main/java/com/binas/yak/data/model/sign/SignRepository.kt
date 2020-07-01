package com.binas.yak.data.model.sign

import javax.inject.Inject


class SignRepository @Inject internal constructor(var signDao: SignDao): SignRepo {

    override fun getSignStudyFlashcards() = signDao.getSignStudyFlashcards()

    override fun addSignStudyFlashcards(flashcards: List<SignStudyFlashcard>) {
        signDao.addSignStudyFlashcards(flashcards)
    }
}