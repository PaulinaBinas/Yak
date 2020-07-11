package com.binas.yak.ui.study.sign.reviseWithDecision.presenter

import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.study.sign.reviseWithDecision.interactor.SignReviseWithDecisionInteractor
import com.binas.yak.ui.study.sign.reviseWithDecision.view.SignReviseWithDecisionView
import com.binas.yak.util.DailyFlashcardQueue
import com.binas.yak.util.SpacedRepetitionScheduler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignReviseWithDecisionPresenterImpl<V: SignReviseWithDecisionView, I: SignReviseWithDecisionInteractor>
    @Inject internal constructor(interactor: I, var scheduler: SpacedRepetitionScheduler, var queue: DailyFlashcardQueue): BasePresenter<V, I>(interactor = interactor), SignReviseWithDecisionPresenter<V, I> {

    override fun start(id: Long) {
        interactor?.let {
            var coroutine = GlobalScope.launch {
                var card = it.getSignRevisionFlashcard(id)
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

    override fun reviseCard(id: Long, remembered: Boolean) {
        interactor?.let {
            var coroutine = GlobalScope.launch {
                var card = it.getSignRevisionFlashcard(id)
                if (card != null) {
                    scheduler?.schedule(card, remembered)
                    it.saveCard(card)
                    queue.removeFlashcard()
                    if(!remembered) {
                        queue.addFlashcard(card)
                    }
                }
            }
            while (!coroutine.isCompleted){}
        }
    }

}