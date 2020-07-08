package com.binas.yak.ui.study.sign.learn.writing.presenter

import com.binas.yak.data.model.sign.SignRevisionFlashcard
import com.binas.yak.data.preferences.PreferenceHelper
import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.study.sign.learn.writing.interactor.LearnSignWritingInteractor
import com.binas.yak.ui.study.sign.learn.writing.view.LearnSignWritingView
import com.binas.yak.util.DailyFlashcardQueue
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class LearnSignWritingPresenterImpl<V: LearnSignWritingView, I: LearnSignWritingInteractor>
@Inject internal constructor(interactor: I, var queue: DailyFlashcardQueue, var preferenceHelper: PreferenceHelper): BasePresenter<V, I>(interactor = interactor), LearnSignWritingPresenter<V, I> {

    override fun scheduleReviewCards(id: Long) {
        interactor?.let {
            var cards: List<SignRevisionFlashcard> = ArrayList()
            var coroutine = GlobalScope.launch {
                it.scheduleReviewsOfSign(id)
                cards = it.getAllMatchingRevisionFlashcards(id)
                preferenceHelper.setNumberOfElementsStudied(preferenceHelper.getNumberOfElementsStudied() + 1)
            }
            while(!coroutine.isCompleted){}
            queue.removeFlashcard()
            for(item in cards) {
                queue.addFlashcard(item)
            }
        }
    }

    override fun markCardAsStudied(id: Long) {
        interactor?.let {
            GlobalScope.launch {
                it.markCardAsStudied(id)
            }
        }
    }
}