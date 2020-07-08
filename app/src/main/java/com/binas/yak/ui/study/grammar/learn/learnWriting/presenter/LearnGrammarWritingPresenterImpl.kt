package com.binas.yak.ui.study.grammar.learn.learnWriting.presenter

import com.binas.yak.data.preferences.PreferenceHelper
import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.study.grammar.learn.learnWriting.interactor.LearnGrammarWritingInteractor
import com.binas.yak.ui.study.grammar.learn.learnWriting.view.LearnGrammarWritingView
import com.binas.yak.util.DailyFlashcardQueue
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class LearnGrammarWritingPresenterImpl<V: LearnGrammarWritingView, I: LearnGrammarWritingInteractor>
@Inject internal constructor(interactor: I, var queue: DailyFlashcardQueue, var preferenceHelper: PreferenceHelper): BasePresenter<V, I>(interactor = interactor), LearnGrammarWritingPresenter<V, I>{

    override fun scheduleReviewCards(id: Long) {
        interactor?.let {
            GlobalScope.launch {
                it.scheduleReviewsOfGrammar(id)
                queue.removeFlashcard()
                for(item in it.getAllMatchingRevisionFlashcards(id)) {
                    queue.addFlashcard(item)
                }
                preferenceHelper.setNumberOfElementsStudied(preferenceHelper.getNumberOfElementsStudied() + 1)
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