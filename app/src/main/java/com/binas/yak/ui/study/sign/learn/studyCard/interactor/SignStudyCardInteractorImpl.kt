package com.binas.yak.ui.study.sign.learn.studyCard.interactor

import com.binas.yak.data.model.translation.Translation
import com.binas.yak.data.model.sign.Sign
import com.binas.yak.data.model.sign.SignRepository
import com.binas.yak.data.model.sign.SignStudyFlashcard
import com.binas.yak.data.model.translation.TranslationRepository
import javax.inject.Inject

class SignStudyCardInteractorImpl @Inject internal constructor(var signRepo: SignRepository, var translationRepo: TranslationRepository): SignStudyCardInteractor {

    override fun getSignStudyCard(id: Long): SignStudyFlashcard? {
        return signRepo.getSignStudyFlashcardById(id)
    }

    override fun getSign(id: Long): Sign? {
        return signRepo.getSignByFlashcardId(id)
    }

    override fun getTranslation(id: Long?): Translation? {
        var translation: Translation? = null
        if (id != null) {
            translation = translationRepo.getTranslation(id)
        }
        return translation
    }
}