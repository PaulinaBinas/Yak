package com.binas.yak.ui.study.vocabulary.learn.studyCard.interactor

import com.binas.yak.data.model.translation.Translation
import com.binas.yak.data.model.translation.TranslationRepository
import com.binas.yak.data.model.vocabulary.Vocabulary
import com.binas.yak.data.model.vocabulary.VocabularyRepository
import com.binas.yak.data.model.vocabulary.VocabularyRevisionFlashcard
import com.binas.yak.data.model.vocabulary.VocabularyStudyFlashcard
import javax.inject.Inject

class VocabularyStudyCardInteractorImpl @Inject internal constructor(var vocabRepo: VocabularyRepository, var translationRepo: TranslationRepository): VocabularyStudyCardInteractor {

    override fun getVocabulary(id: Long): Vocabulary {
        return vocabRepo.getVocabularyById(id)
    }

    override fun getVocabularyStudyFlashcard(id: Long): VocabularyStudyFlashcard {
        return vocabRepo.getVocabularyStudyFlashcardById(id)
    }

    override fun getTranslation(id: Long): Translation {
        return translationRepo.getTranslation(id)
    }

}