package com.binas.yak.ui.study.vocabulary.learn.learnWriting.presenter

import com.binas.yak.data.model.grammar.GrammarRevisionFlashcard
import com.binas.yak.data.model.studyDay.StudyDay
import com.binas.yak.data.model.vocabulary.VocabularyRevisionFlashcard
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
            var cards: List<VocabularyRevisionFlashcard> = ArrayList()
            var coroutine = GlobalScope.launch {
                it.scheduleReviewsOfVocabulary(id)
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