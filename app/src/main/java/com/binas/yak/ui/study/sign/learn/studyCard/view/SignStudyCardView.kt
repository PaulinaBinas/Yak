package com.binas.yak.ui.study.sign.learn.studyCard.view

import com.binas.yak.data.model.sign.Sign
import com.binas.yak.data.model.sign.SignStudyFlashcard
import com.binas.yak.data.model.translation.Translation
import com.binas.yak.ui.base.view.BaseView

interface SignStudyCardView: BaseView {

    fun setContent(card: SignStudyFlashcard?, sign: Sign?, translation: Translation?)

    fun loadImage()
}