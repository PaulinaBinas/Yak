package com.binas.yak.ui.study.sign.reviseWithDecision.interactor

import com.binas.yak.data.model.RevisionFlashcard
import com.binas.yak.data.model.sign.Sign
import com.binas.yak.data.model.sign.SignRevisionFlashcard
import com.binas.yak.ui.base.interactor.Interactor

interface SignReviseWithDecisionInteractor: Interactor {

    fun getSignRevisionFlashcard(id: Long): SignRevisionFlashcard

    fun getSign(id: Long): Sign

    fun saveCard(card: SignRevisionFlashcard)
    fun getUserStudyTime(id: Long): Double
    fun setUserStudyTime(id: Long, time: Double)
    fun updateTotalTime(email: String, totalMinutes: Double)
    fun updateReviseFlashcards(email: String, id: String, card: RevisionFlashcard)
}