package com.binas.yak.ui.study.common.compareWriting.interactor

import com.binas.yak.data.model.RevisionFlashcard
import com.binas.yak.ui.base.interactor.Interactor

interface CompareWritingInteractor: Interactor {

    fun getCard(id: Long, type: String): RevisionFlashcard?

    fun saveCard(card: RevisionFlashcard, type: String)
    fun getUserStudyTime(id: Long): Double
    fun setUserStudyTime(id: Long, time: Double)
}