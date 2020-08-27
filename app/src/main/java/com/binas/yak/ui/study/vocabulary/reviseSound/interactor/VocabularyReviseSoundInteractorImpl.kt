package com.binas.yak.ui.study.vocabulary.reviseSound.interactor

import com.binas.yak.data.model.vocabulary.Vocabulary
import com.binas.yak.data.model.vocabulary.VocabularyRepository
import com.binas.yak.data.model.vocabulary.VocabularyRevisionFlashcard
import javax.inject.Inject

open class VocabularyReviseSoundInteractorImpl @Inject internal constructor(var vocabularyRepo: VocabularyRepository): VocabularyReviseSoundInteractor {

    override fun getVocabularyRevisionFlashcard(id: Long): VocabularyRevisionFlashcard {
        return vocabularyRepo.getVocabularyRevisionFlashcardById(id)
    }

    override fun getVocabulary(id: Long): Vocabulary {
        return vocabularyRepo.getVocabularyById(id)
    }
}