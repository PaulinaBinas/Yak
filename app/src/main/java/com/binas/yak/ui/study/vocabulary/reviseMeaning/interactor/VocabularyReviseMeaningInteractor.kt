package com.binas.yak.ui.study.vocabulary.reviseMeaning.interactor

import com.binas.yak.data.model.translation.Translation
import com.binas.yak.data.model.vocabulary.Vocabulary
import com.binas.yak.data.model.vocabulary.VocabularyRevisionFlashcard
import com.binas.yak.ui.base.interactor.Interactor

interface VocabularyReviseMeaningInteractor: Interactor {

    fun getVocabularyRevisionFlashcard(id: Long): VocabularyRevisionFlashcard

    fun getVocabulary(id: Long): Vocabulary

    fun getTranslation(id: Long): Translation
}