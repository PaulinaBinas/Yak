package com.binas.yak.ui.study.presenter

import com.binas.yak.data.model.Flashcard
import com.binas.yak.data.model.sign.SignStudyFlashcard
import com.binas.yak.data.model.studyOrder.StudyOrder
import com.binas.yak.data.preferences.PreferenceHelper
import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.study.interactor.StudyInteractor
import com.binas.yak.ui.study.view.StudyView
import com.binas.yak.util.DailyFlashcardQueue
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class StudyPresenterImpl<V: StudyView, I: StudyInteractor> @Inject internal constructor(var preferenceHelper: PreferenceHelper, interactor: I, var queue: DailyFlashcardQueue): BasePresenter<V, I>(interactor = interactor), StudyPresenter<V, I> {

    override fun start() {
        interactor?.let {
            var studyOrder: List<StudyOrder> = ArrayList()
            var newDailyItems = 0
            var dailyLimit = preferenceHelper.getDailyCardLimit()
            lateinit var nextItem: Flashcard
            var coroutine = GlobalScope.launch {
                studyOrder = it.getStudyOrderList()
            }
            while (!coroutine.isCompleted){}
            if(queue.isQueueEmpty()) {
                for(item in studyOrder) {
                    if (newDailyItems < dailyLimit) {
                        var coroutine = GlobalScope.launch {
                            if (item.signStudyId != null) {
                                queue.addFlashcard(it.getSignStudyCard(item.signStudyId!!))
                            } else if (item.vocabularyStudyId != null) {
                                queue.addFlashcard(it.getVocabularyStudyFlashcard(item.vocabularyStudyId!!))
                            } else if (item.grammarStudyId != null) {
                                queue.addFlashcard(it.getGrammarStudyFlashcard(item.grammarStudyId!!))
                            }
                        }
                        while (!coroutine.isCompleted) {}
                        newDailyItems++
                    }
                }
                var coroutine = GlobalScope.launch {

                }
                while (!coroutine.isCompleted){}
            }
            queue.getNextFlashcard().let { nextItem = it!! }
            if(nextItem != null) {
                when(nextItem){
                    is SignStudyFlashcard -> getView()?.onClickGoToStudySign((nextItem as SignStudyFlashcard).id)
                    else -> getView()
                }
            } else {
                getView()?.displayStudyOver()
            }
        }
    }
}