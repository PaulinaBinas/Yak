package com.binas.yak.ui.study.vocabulary.reviseSound.interactor

import com.binas.yak.data.model.vocabulary.Vocabulary
import com.binas.yak.data.model.vocabulary.VocabularyRevisionFlashcard
import com.binas.yak.ui.base.interactor.Interactor

interface VocabularyReviseSoundInteractor: Interactor {

    fun getVocabularyRevisionFlashcard(id: Long): VocabularyRevisionFlashcard

    fun getVocabulary(id: Long): Vocabulary
}