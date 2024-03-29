package com.binas.yak.ui.study.common.meaningCheck.interactor

import com.binas.yak.data.model.vocabulary.VocabularyRevisionFlashcard
import com.binas.yak.ui.base.interactor.Interactor

interface MeaningCheckInteractor: Interactor {

    fun saveCard(card: VocabularyRevisionFlashcard)

    fun getCard(id: Long): VocabularyRevisionFlashcard
    fun getUserStudyTime(id: Long): Double
    fun setUserStudyTime(id: Long, time: Double)
    fun updateTotalTime(email: String, time: Double)
    fun updateRevisedFlashcards(email: String, card: VocabularyRevisionFlashcard)
}