package com.binas.yak.ui.study.sign.reviseWithDecision.interactor

import com.binas.yak.data.model.sign.Sign
import com.binas.yak.data.model.sign.SignRepository
import com.binas.yak.data.model.sign.SignRevisionFlashcard
import javax.inject.Inject

class SignReviseWithDecisionInteractorImpl @Inject internal constructor(var signRepo: SignRepository): SignReviseWithDecisionInteractor {

    override fun getSignRevisionFlashcard(id: Long): SignRevisionFlashcard {
        return signRepo.getSignRevisionFlashcardById(id)
    }

    override fun getSign(id: Long): Sign {
        return signRepo.getSignByFlashcardId(id)
    }
}