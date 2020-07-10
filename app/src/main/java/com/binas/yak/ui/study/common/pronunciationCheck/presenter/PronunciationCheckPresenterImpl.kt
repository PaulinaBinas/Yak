package com.binas.yak.ui.study.common.pronunciationCheck.presenter

import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.study.common.pronunciationCheck.interactor.PronunciationCheckInteractor
import com.binas.yak.ui.study.common.pronunciationCheck.view.PronunciationCheckView
import com.binas.yak.util.DailyFlashcardQueue
import com.binas.yak.util.SpacedRepetitionScheduler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class PronunciationCheckPresenterImpl<V: PronunciationCheckView, I: PronunciationCheckInteractor>
@Inject internal constructor(interactor: I, var queue: DailyFlashcardQueue, var scheduler: SpacedRepetitionScheduler): BasePresenter<V, I>(interactor = interactor), PronunciationCheckPresenter<V, I> {

    override fun reviseCard(id: Long, type: String, remembered: Boolean) {
        interactor?.let {
            var coroutine = GlobalScope.launch {
                var card = it.getCard(id, type)
                if (card != null) {
                    scheduler?.schedule(card, remembered)
                    it.saveCard(card, type)
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