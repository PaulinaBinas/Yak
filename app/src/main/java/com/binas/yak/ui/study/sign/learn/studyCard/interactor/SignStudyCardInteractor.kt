package com.binas.yak.ui.study.sign.learn.studyCard.interactor

import com.binas.yak.data.model.translation.Translation
import com.binas.yak.data.model.sign.Sign
import com.binas.yak.data.model.sign.SignStudyFlashcard
import com.binas.yak.ui.base.interactor.Interactor

interface SignStudyCardInteractor: Interactor {

    fun getSignStudyCard(id: Long): SignStudyFlashcard?

    fun getSign(id: Long): Sign?

    fun getTranslation(id: Long?): Translation?
}