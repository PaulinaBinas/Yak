package com.binas.yak.ui.study.common.compareWriting.presenter

import com.binas.yak.data.preferences.PreferenceHelper
import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.study.common.compareWriting.interactor.CompareWritingInteractor
import com.binas.yak.ui.study.common.compareWriting.view.CompareWritingView
import com.binas.yak.util.DailyFlashcardQueue
import com.binas.yak.util.SpacedRepetitionScheduler
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class CompareWritingPresenterImpl<V: CompareWritingView, I: CompareWritingInteractor>
@Inject internal constructor(interactor: I, var queue: DailyFlashcardQueue, var scheduler: SpacedRepetitionScheduler, var preferenceHelper: PreferenceHelper): BasePresenter<V, I>(interactor = interactor),CompareWritingPresenter<V, I> {

    override fun reviseCard(type: String, id: Long, remembered: Boolean) {
        interactor?.let {
            var timeStudiedMilies = getView()?.getDuration()
            var coroutine = GlobalScope.launch {
                updateTimeStudied(it, timeStudiedMilies)
                var card = it.getCard(id, type)
                if (card != null) {
                    scheduler?.schedule(card, remembered)
                    it.saveCard(card, type)
                    preferenceHelper.getCurrentUserEmail()?.let { email ->
                        it.updateRevisedFlashcards(email, card, type)
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

    private fun updateTimeStudied(it: CompareWritingInteractor, time: Long?) {
        var id = preferenceHelper.getCurrentUserId()
        var currentTotal = it.getUserStudyTime(id)
        var totalMinutes = currentTotal + ((time!!.toDouble() / 1000 ) / 60)
        it.setUserStudyTime(id, totalMinutes)
        preferenceHelper.getCurrentUserEmail()?.let { email ->
            it.updateTimeStudied(email, totalMinutes)
        }
    }
}