package com.binas.yak.ui.study.common.pronunciationCheck.presenter

import com.binas.yak.data.preferences.PreferenceHelper
import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.study.common.pronunciationCheck.interactor.PronunciationCheckInteractor
import com.binas.yak.ui.study.common.pronunciationCheck.view.PronunciationCheckView
import com.binas.yak.util.DailyFlashcardQueue
import com.binas.yak.util.SpacedRepetitionScheduler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class PronunciationCheckPresenterImpl<V: PronunciationCheckView, I: PronunciationCheckInteractor>
@Inject internal constructor(interactor: I, var queue: DailyFlashcardQueue, var scheduler: SpacedRepetitionScheduler, var preferenceHelper: PreferenceHelper): BasePresenter<V, I>(interactor = interactor), PronunciationCheckPresenter<V, I> {

    override fun reviseCard(id: Long, type: String, remembered: Boolean) {
        interactor?.let {
            var timeStudiedMilies = getView()?.getDuration()
            var coroutine = GlobalScope.launch {
                updateTimeStudied(it, timeStudiedMilies)
                var card = it.getCard(id, type)
                if (card != null) {
                    scheduler?.schedule(card, remembered)
                    it.saveCard(card, type)
                    preferenceHelper.getCurrentUserEmail()?.let { email ->
                        var idNumber = when(type) {
                            "sign" -> "1" + card.id.toString()
                            "vocabulary" -> "2" + card.id.toString()
                            "grammar" -> "3" + card.id.toString()
                            else -> card.id.toString()
                        }
                        it.updateRevisedFlashcards(email, idNumber, card)
                    }
                    queue.removeFlashcard()
                    if(!remembered) {
                        queue.addFlashcard(card)
                    }
                }
            }
            while (!coroutine.isCompleted){}
        }
    }

    private fun updateTimeStudied(it: PronunciationCheckInteractor, time: Long?) {
        var id = preferenceHelper.getCurrentUserId()
        var currentTotal = it.getUserStudyTime(id)
        var totalMinutes = currentTotal + ((time!!.toDouble() / 1000 ) / 60)
        it.setUserStudyTime(id, totalMinutes)
        preferenceHelper.getCurrentUserEmail()?.let { email ->
            it.updateTotalMinutes(email, totalMinutes)
        }
    }
}