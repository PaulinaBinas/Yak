package com.binas.yak.ui.study.sign.reviseWriting.interactor

import com.binas.yak.data.model.sign.Sign
import com.binas.yak.data.model.sign.SignRevisionFlashcard
import com.binas.yak.ui.base.interactor.Interactor

interface SignReviseWritingInteractor: Interactor {

    fun getSignRevisionFlashcard(id: Long): SignRevisionFlashcard

    fun getSign(id: Long): Sign
}