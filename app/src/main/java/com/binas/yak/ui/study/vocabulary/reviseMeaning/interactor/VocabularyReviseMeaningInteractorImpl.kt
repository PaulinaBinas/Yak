package com.binas.yak.ui.study.vocabulary.reviseMeaning.interactor

import com.binas.yak.data.model.translation.Translation
import com.binas.yak.data.model.translation.TranslationRepository
import com.binas.yak.data.model.vocabulary.Vocabulary
import com.binas.yak.data.model.vocabulary.VocabularyRepository
import com.binas.yak.data.model.vocabulary.VocabularyRevisionFlashcard
import javax.inject.Inject

class VocabularyReviseMeaningInteractorImpl @Inject internal constructor(var vocabularyRepo: VocabularyRepository, var translationRepo: TranslationRepository): VocabularyReviseMeaningInteractor {

    override fun getVocabularyRevisionFlashcard(id: Long): VocabularyRevisionFlashcard {
        return vocabularyRepo.getVocabularyRevisionFlashcardById(id)
    }

    override fun getVocabulary(id: Long): Vocabulary {
        return vocabularyRepo.getVocabularyById(id)
    }

    override fun getTranslation(id: Long): Translation {
        return translationRepo.getTranslation(id)
    }
}