package com.binas.yak.ui.study.sign.reviseSound.view

import com.binas.yak.data.model.sign.Sign
import com.binas.yak.data.model.sign.SignRevisionFlashcard
import com.binas.yak.ui.base.view.BaseView

interface SignReviseSoundView: BaseView {

    fun setContent(card: SignRevisionFlashcard, sign: Sign)
    fun loadImage()
}