package com.binas.yak.ui.study.common.pronunciationCheck.interactor

import com.binas.yak.data.model.RevisionFlashcard
import com.binas.yak.data.model.sign.SignRepository
import com.binas.yak.data.model.sign.SignRevisionFlashcard
import com.binas.yak.data.model.vocabulary.VocabularyRepository
import com.binas.yak.data.model.vocabulary.VocabularyRevisionFlashcard
import javax.inject.Inject

class PronunciationCheckInteractorImpl @Inject internal constructor(var signRepo: SignRepository, var vocabularyRepo: VocabularyRepository): PronunciationCheckInteractor {

    override fun getCard(id: Long, type: String): RevisionFlashcard? {
        when(type) {
            "sign" -> return signRepo.getSignRevisionFlashcardById(id)
            "vocabulary" -> return vocabularyRepo.getVocabularyRevisionFlashcardById(id)
        }
        return null
    }

    override fun saveCard(card: RevisionFlashcard, type: String) {
        when(type) {
            "sign" -> return signRepo.saveSignRevisionFlashcard(card as SignRevisionFlashcard)
            "vocabulary" -> return vocabularyRepo.saveVocabularyRevisionFlashcard(card as VocabularyRevisionFlashcard)
        }
    }
}