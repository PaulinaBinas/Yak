package com.binas.yak.ui.study.vocabulary.learn.learnWriting.interactor

import com.binas.yak.data.model.vocabulary.VocabularyRevisionFlashcard
import com.binas.yak.ui.base.interactor.Interactor

interface LearnVocabularyWritingInteractor: Interactor {

    fun scheduleReviewsOfVocabulary(id: Long)

    fun getAllMatchingRevisionFlashcards(id: Long): List<VocabularyRevisionFlashcard>

    fun markCardAsStudied(id: Long)
}