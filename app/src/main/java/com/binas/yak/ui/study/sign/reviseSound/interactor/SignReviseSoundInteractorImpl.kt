package com.binas.yak.ui.study.sign.reviseSound.interactor

import com.binas.yak.data.model.sign.Sign
import com.binas.yak.data.model.sign.SignRepository
import com.binas.yak.data.model.sign.SignRevisionFlashcard
import javax.inject.Inject

open class SignReviseSoundInteractorImpl @Inject internal constructor(var signRepo: SignRepository): SignReviseSoundInteractor {

    override fun getSignRevisionCard(id: Long): SignRevisionFlashcard {
        return signRepo.getSignRevisionFlashcardById(id)
    }

    override fun getSign(id: Long): Sign {
        return signRepo.getSignByFlashcardId(id)
    }
}