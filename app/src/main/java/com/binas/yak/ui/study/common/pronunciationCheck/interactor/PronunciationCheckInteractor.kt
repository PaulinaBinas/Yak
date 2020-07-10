package com.binas.yak.ui.study.common.pronunciationCheck.interactor

import com.binas.yak.data.model.RevisionFlashcard
import com.binas.yak.ui.base.interactor.Interactor

interface PronunciationCheckInteractor: Interactor {

    fun getCard(id: Long, type: String): RevisionFlashcard?

    fun saveCard(card: RevisionFlashcard, type: String)
}