package com.binas.yak.ui.study.common.meaningCheck.presenter

import com.binas.yak.data.preferences.PreferenceHelper
import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.study.common.meaningCheck.interactor.MeaningCheckInteractor
import com.binas.yak.ui.study.common.meaningCheck.view.MeaningCheckView
import com.binas.yak.util.DailyFlashcardQueue
import com.binas.yak.util.SpacedRepetitionScheduler
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MeaningCheckPresenterImpl<V: MeaningCheckView, I: MeaningCheckInteractor>
@Inject internal constructor(interactor: I, var queue: DailyFlashcardQueue, var scheduler: SpacedRepetitionScheduler, var preferenceHelper: PreferenceHelper): BasePresenter<V, I>(interactor = interactor), MeaningCheckPresenter<V, I> {

    override fun reviseCard(id: Long, remembered: Boolean) {
        interactor?.let {
            var coroutine = GlobalScope.launch {
                var card = it.getCard(id)
                if (card != null) {
                    scheduler?.schedule(card, remembered)
                    it.saveCard(card)
                    var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
                    preferenceHelper.getCurrentUserEmail()?.let { email ->
                        var idNumber = "2" + card.id.toString()
                        firestore.collection("users").document(email)
                            .collection("revisedFlashcards").document(idNumber).set(card)
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
}