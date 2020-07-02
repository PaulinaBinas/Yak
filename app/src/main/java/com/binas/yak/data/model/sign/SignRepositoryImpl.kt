package com.binas.yak.data.model.sign

import javax.inject.Inject


class SignRepositoryImpl @Inject internal constructor(var signDao: SignDao): SignRepository {

    override fun getSignStudyFlashcards() = signDao.getSignStudyFlashcards()

    override fun getSignStudyFlashcardById(id: Long): SignStudyFlashcard {
        return signDao.getSignStudyFlashcardById(id)
    }

    override fun getSignByFlashcardId(id: Long): Sign {
        return signDao.getSignByFlashcardId(id)
    }

    override fun addSignStudyFlashcards(flashcards: List<SignStudyFlashcard>) {
        signDao.addSignStudyFlashcards(flashcards)
    }
}