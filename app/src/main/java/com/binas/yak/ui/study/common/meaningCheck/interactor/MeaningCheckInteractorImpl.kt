package com.binas.yak.ui.study.common.meaningCheck.interactor

import com.binas.yak.data.model.user.UserRepository
import com.binas.yak.data.model.vocabulary.VocabularyRepository
import com.binas.yak.data.model.vocabulary.VocabularyRevisionFlashcard
import javax.inject.Inject

class MeaningCheckInteractorImpl @Inject internal constructor(var userRepo: UserRepository, var vocabularyRepo: VocabularyRepository): MeaningCheckInteractor {

    override fun saveCard(card: VocabularyRevisionFlashcard) {
        vocabularyRepo.saveVocabularyRevisionFlashcard(card)
    }

    override fun getCard(id: Long): VocabularyRevisionFlashcard {
        return vocabularyRepo.getVocabularyRevisionFlashcardById(id)
    }

    override fun getUserStudyTime(id: Long): Double {
        return userRepo.getTotalMinutesStudiedByUserId(id)
    }

    override fun setUserStudyTime(id: Long, time: Double) {
        userRepo.setTotalMinutesStudiedByUserId(id, time)
    }
}