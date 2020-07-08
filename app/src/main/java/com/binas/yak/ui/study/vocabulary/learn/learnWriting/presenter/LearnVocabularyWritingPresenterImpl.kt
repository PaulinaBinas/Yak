package com.binas.yak.ui.study.vocabulary.learn.learnWriting.presenter

import com.binas.yak.data.preferences.PreferenceHelper
import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.study.vocabulary.learn.learnWriting.interactor.LearnVocabularyWritingInteractor
import com.binas.yak.ui.study.vocabulary.learn.learnWriting.view.LearnVocabularyWritingView
import com.binas.yak.util.DailyFlashcardQueue
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class LearnVocabularyWritingPresenterImpl<V: LearnVocabularyWritingView, I: LearnVocabularyWritingInteractor>
@Inject internal constructor(interactor: I, var queue: DailyFlashcardQueue, var preferenceHelper: PreferenceHelper): BasePresenter<V, I>(interactor = interactor), LearnVocabularyWritingPresenter<V, I> {

    override fun scheduleReviewCards(id: Long) {
        interactor?.let {
            GlobalScope.launch {
                it.scheduleReviewsOfVocabulary(id)
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