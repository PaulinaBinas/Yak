package com.binas.yak.data.model.sign

import com.binas.yak.data.model.RevisionType
import javax.inject.Inject
import kotlin.math.sign


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
}