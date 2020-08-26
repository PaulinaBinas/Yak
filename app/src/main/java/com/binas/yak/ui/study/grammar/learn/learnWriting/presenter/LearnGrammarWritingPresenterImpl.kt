package com.binas.yak.ui.study.grammar.learn.learnWriting.presenter

import com.binas.yak.data.model.grammar.GrammarRevisionFlashcard
import com.binas.yak.data.model.studyDay.StudyDay
import com.binas.yak.data.preferences.PreferenceHelper
import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.study.grammar.learn.learnWriting.interactor.LearnGrammarWritingInteractor
import com.binas.yak.ui.study.grammar.learn.learnWriting.view.LearnGrammarWritingView
import com.binas.yak.util.DailyFlashcardQueue
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class LearnGrammarWritingPresenterImpl<V: LearnGrammarWritingView, I: LearnGrammarWritingInteractor>
@Inject internal constructor(interactor: I, var queue: DailyFlashcardQueue, var preferenceHelper: PreferenceHelper): BasePresenter<V, I>(interactor = interactor), LearnGrammarWritingPresenter<V, I>{

    override fun scheduleReviewCards(id: Long) {
        interactor?.let {
            var timeStudiedMilies = getView()?.getDuration()
            var cards: List<GrammarRevisionFlashcard> = ArrayList()
            var coroutine = GlobalScope.launch {
                cards = it.getAllMatchingRevisionFlashcards(id)
                updateTimeStudied(it, timeStudiedMilies)
                it.scheduleReviewsOfGrammar(id)
                GlobalScope.launch {
                    var studyDay = it.getStudyDay()
                    if(studyDay == null) {
                        studyDay = StudyDay(null)
                    }
                    studyDay.elementsStudied = studyDay.elementsStudied?.plus(1)
                    it.saveStudyDay(studyDay)
                    var flashcard = it.getGrammarStudyFlashcard(id)
                    var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
                    preferenceHelper.getCurrentUserEmail()?.let { email ->
                        if(email.isNotEmpty()) {
                            var user = firestore.collection("users").document(email)
                            user.update("studiedFlashcards", FieldValue.arrayUnion(flashcard))
                            val data = hashMapOf(
                                "day" to studyDay.date.toString(),
                                "elementsStudied" to studyDay.elementsStudied
                            )
                            user.collection("studyDays").document(studyDay.date.toString()).set(data)
                        }
                    }
                }
            }
            while(!coroutine.isCompleted){}
            queue.removeFlashcard()
            for(item in cards) {
                queue.addFlashcard(item)
            }
        }
    }

    private fun updateTimeStudied(it: LearnGrammarWritingInteractor, time: Long?) {
        var id = preferenceHelper.getCurrentUserId()
        var currentTotal = it.getUserStudyTime(id)
        var totalMinutes = currentTotal + ((time!!.toDouble() / 1000 ) / 60)
        it.setUserStudyTime(id, totalMinutes)
        var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
        preferenceHelper.getCurrentUserEmail()?.let { email ->
            if(email.isNotEmpty()) {
                firestore.collection("users").document(email)
                    .update("totalMinutesStudied", totalMinutes)
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