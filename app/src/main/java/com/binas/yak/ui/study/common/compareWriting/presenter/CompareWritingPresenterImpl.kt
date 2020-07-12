package com.binas.yak.ui.study.common.compareWriting.presenter

import com.binas.yak.data.preferences.PreferenceHelper
import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.study.common.compareWriting.interactor.CompareWritingInteractor
import com.binas.yak.ui.study.common.compareWriting.view.CompareWritingView
import com.binas.yak.util.DailyFlashcardQueue
import com.binas.yak.util.SpacedRepetitionScheduler
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class CompareWritingPresenterImpl<V: CompareWritingView, I: CompareWritingInteractor>
@Inject internal constructor(interactor: I, var queue: DailyFlashcardQueue, var scheduler: SpacedRepetitionScheduler, var preferenceHelper: PreferenceHelper): BasePresenter<V, I>(interactor = interactor),CompareWritingPresenter<V, I> {

    override fun reviseCard(type: String, id: Long, remembered: Boolean) {
        interactor?.let {
            var coroutine = GlobalScope.launch {
                var card = it.getCard(id, type)
                if (card != null) {
                    scheduler?.schedule(card, remembered)
                    it.saveCard(card, type)
                    var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
                    preferenceHelper.getCurrentUserEmail()?.let { email ->
                        var idNumber = when(type) {
                            "sign" -> "1" + card.id.toString()
                            "vocabulary" -> "2" + card.id.toString()
                            "grammar" -> "3" + card.id.toString()
                            else -> card.id.toString()
                        }
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