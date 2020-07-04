package com.binas.yak.ui.study.sign.reviseWithDecision.view

import com.binas.yak.data.model.sign.Sign
import com.binas.yak.data.model.sign.SignRevisionFlashcard
import com.binas.yak.ui.base.view.BaseView

interface SignReviseWithDecisionView: BaseView {

    fun setContent(card: SignRevisionFlashcard, sign: Sign, incorrectSign: Sign?)

    fun clickSoundButton()
}