package com.binas.yak.ui.study.grammar.learn.learnWriting.presenter

import com.binas.yak.data.model.grammar.GrammarRevisionFlashcard
import com.binas.yak.data.model.sign.SignRevisionFlashcard
import com.binas.yak.data.model.studyDay.StudyDay
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
            var cards: List<GrammarRevisionFlashcard> = ArrayList()
            var coroutine = GlobalScope.launch {
                it.scheduleReviewsOfGrammar(id)
                cards = it.getAllMatchingRevisionFlashcards(id)
                GlobalScope.launch {
                    var studyDay = it.getStudyDay()
                    if(studyDay == null) {
                        studyDay = StudyDay(-1L)
                    }
                    studyDay.elementsStudied = studyDay.elementsStudied?.plus(1)
                    it.saveStudyDay(studyDay)
                }
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