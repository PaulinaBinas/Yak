package com.binas.yak.ui.study.common.meaningCheck.presenter

import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.study.common.meaningCheck.interactor.MeaningCheckInteractor
import com.binas.yak.ui.study.common.meaningCheck.view.MeaningCheckView
import com.binas.yak.util.DailyFlashcardQueue
import com.binas.yak.util.SpacedRepetitionScheduler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MeaningCheckPresenterImpl<V: MeaningCheckView, I: MeaningCheckInteractor>
@Inject internal constructor(interactor: I, var queue: DailyFlashcardQueue, var scheduler: SpacedRepetitionScheduler): BasePresenter<V, I>(interactor = interactor), MeaningCheckPresenter<V, I> {

    override fun reviseCard(id: Long, remembered: Boolean) {
        interactor?.let {
            var coroutine = GlobalScope.launch {
                var card = it.getCard(id)
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