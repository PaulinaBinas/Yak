package com.binas.yak.ui.study.sign.reviseWithDecision.presenter

import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.study.sign.reviseWithDecision.interactor.SignReviseWithDecisionInteractor
import com.binas.yak.ui.study.sign.reviseWithDecision.view.SignReviseWithDecisionView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignReviseWithDecisionPresenterImpl<V: SignReviseWithDecisionView, I: SignReviseWithDecisionInteractor>
    @Inject internal constructor(interactor: I): BasePresenter<V, I>(interactor = interactor), SignReviseWithDecisionPresenter<V, I> {

    override fun start() {
        interactor?.let {
            var coroutine = GlobalScope.launch {
                var card = it.getSignRevisionFlashcard(3)
                var sign = it.getSign(card.signId)
                var wrongSign = card.comparisonSignId?.let { it1 -> it.getSign(it1) }
                getView()?.setContent(card, sign, wrongSign)
            }
            while (!coroutine.isCompleted) { }
            if(coroutine.isCompleted) {
                getView()?.clickSoundButton()
            }
        }
    }

}