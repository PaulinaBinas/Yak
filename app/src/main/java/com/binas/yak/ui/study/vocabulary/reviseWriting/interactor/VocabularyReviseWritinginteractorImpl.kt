package com.binas.yak.ui.study.vocabulary.reviseWriting.interactor

import com.binas.yak.data.model.vocabulary.Vocabulary
import com.binas.yak.data.model.vocabulary.VocabularyRepository
import com.binas.yak.data.model.vocabulary.VocabularyRevisionFlashcard
import javax.inject.Inject

class VocabularyReviseWritinginteractorImpl @Inject internal constructor(var vocabularyRepo: VocabularyRepository): VocabularyReviseWritingInteractor {
    override fun getVocabularyRevisionFlashcard(id: Long): VocabularyRevisionFlashcard {
        return vocabularyRepo.getVocabularyRevisionFlashcardById(id)
    }

    override fun getVocabulary(id: Long): Vocabulary {
        return vocabularyRepo.getVocabularyById(id)
    }
}