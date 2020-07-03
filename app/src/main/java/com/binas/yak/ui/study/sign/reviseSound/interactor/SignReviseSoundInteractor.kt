package com.binas.yak.ui.study.sign.reviseSound.interactor

import com.binas.yak.data.model.sign.Sign
import com.binas.yak.data.model.sign.SignRevisionFlashcard
import com.binas.yak.ui.base.interactor.Interactor

interface SignReviseSoundInteractor: Interactor {

    fun getSignRevisionCard(id: Long): SignRevisionFlashcard

    fun getSign(id: Long): Sign
}