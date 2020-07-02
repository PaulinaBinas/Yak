package com.binas.yak.ui.study.sign.learn.studyCard.presenter

import com.binas.yak.data.model.sign.Sign
import com.binas.yak.data.model.sign.SignStudyFlashcard
import com.binas.yak.data.model.translation.Translation
import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.study.sign.learn.studyCard.interactor.SignStudyCardInteractor
import com.binas.yak.ui.study.sign.learn.studyCard.view.SignStudyCardView
import kotlinx.android.synthetic.main.activity_sign_study_card.*
import kotlinx.android.synthetic.main.fragment_image.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignStudyCardPresenterImpl<V: SignStudyCardView, I: SignStudyCardInteractor>
@Inject internal constructor(interactor: I): BasePresenter<V, I>(interactor = interactor), SignStudyCardPresenter<V, I> {

    override fun start() {
        interactor?.let {
            var coroutine = GlobalScope.launch {
                var card = it.getSignStudyCard(1)
                var sign = it.getSign(card!!.signId)
                var translation = it.getTranslation(sign?.mnemonicDescriptionTranslationId)
                getView()?.setContent(card, sign, translation)
            }
            while(!coroutine.isCompleted) {

            }
            if(coroutine.isCompleted) {
                getView()?.loadImage()
            }
        }
    }

    override fun onAttach(view: V?) {
        super.onAttach(view)
    }

    override fun goToLearnSignStudyCard() {
        TODO("Not yet implemented")
    }


}