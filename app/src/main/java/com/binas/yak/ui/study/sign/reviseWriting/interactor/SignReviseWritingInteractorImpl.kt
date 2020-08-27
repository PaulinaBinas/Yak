package com.binas.yak.ui.study.sign.reviseWriting.interactor

import com.binas.yak.data.model.sign.Sign
import com.binas.yak.data.model.sign.SignRepository
import com.binas.yak.data.model.sign.SignRevisionFlashcard
import com.binas.yak.data.preferences.PreferenceHelper
import com.binas.yak.ui.base.interactor.BaseInteractor
import javax.inject.Inject
import kotlin.math.sign

open class SignReviseWritingInteractorImpl @Inject internal constructor(preferenceHelper: PreferenceHelper, var signRepo: SignRepository):
    BaseInteractor(preferenceHelper), SignReviseWritingInteractor {

    override fun getSignRevisionFlashcard(id: Long): SignRevisionFlashcard {
        return signRepo.getSignRevisionFlashcardById(id)
    }

    override fun getSign(id: Long): Sign {
        return signRepo.getSignByFlashcardId(id)
    }
}