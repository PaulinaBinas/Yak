package com.binas.yak.ui.study.vocabulary.learn.studyCard.interactor

import com.binas.yak.data.model.translation.Translation
import com.binas.yak.data.model.vocabulary.Vocabulary
import com.binas.yak.data.model.vocabulary.VocabularyStudyFlashcard
import com.binas.yak.ui.base.interactor.Interactor

interface VocabularyStudyCardInteractor: Interactor {

    fun getVocabulary(id: Long): Vocabulary
    fun getVocabularyStudyFlashcard(id: Long): VocabularyStudyFlashcard
    fun getTranslation(id: Long): Translation
}