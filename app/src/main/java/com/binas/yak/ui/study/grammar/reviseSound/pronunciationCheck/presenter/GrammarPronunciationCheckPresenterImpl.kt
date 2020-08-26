package com.binas.yak.ui.study.grammar.reviseSound.pronunciationCheck.presenter

import com.binas.yak.data.preferences.PreferenceHelper
import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.study.grammar.reviseSound.pronunciationCheck.interactor.GrammarPronunciationCheckInteractor
import com.binas.yak.ui.study.grammar.reviseSound.pronunciationCheck.view.GrammarPronunciationCheckView
import com.binas.yak.util.DailyFlashcardQueue
import com.binas.yak.util.SpacedRepetitionScheduler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class GrammarPronunciationCheckPresenterImpl<V: GrammarPronunciationCheckView, I: GrammarPronunciationCheckInteractor>
@Inject internal constructor(interactor: I, var queue: DailyFlashcardQueue,var scheduler: SpacedRepetitionScheduler, var preferenceHelper: PreferenceHelper): BasePresenter<V, I>(interactor = interactor), GrammarPronunciationCheckPresenter<V, I> {

    override fun reviseCard(remembered: Boolean, id: Long) {
        interactor?.let {
            var timeStudiedMilies = getView()?.getDuration()
            var coroutine = GlobalScope.launch {
                var card = it.getCard(id)
                scheduler?.schedule(card, remembered)
                it.saveCard(card)
                updateTimeStudied(it, timeStudiedMilies)
                preferenceHelper.getCurrentUserEmail()?.let { email ->
                    it.updateRevisedFlashcards(email, card)
                }
                queue.removeFlashcard()
                if(!remembered) {
                    queue.addFlashcard(card)
                }
            }
            while (!coroutine.isCompleted){}
        }
    }

    private fun updateTimeStudied(it: GrammarPronunciationCheckInteractor, time: Long?) {
        var id = preferenceHelper.getCurrentUserId()
        var currentTotal = it.getUserStudyTime(id)
        var totalMinutes = currentTotal + ((time!!.toDouble() / 1000 ) / 60)
        it.setUserStudyTime(id, totalMinutes)
        preferenceHelper.getCurrentUserEmail()?.let { email ->
            it.updateTimeStudied(email, totalMinutes)
        }
    }
}