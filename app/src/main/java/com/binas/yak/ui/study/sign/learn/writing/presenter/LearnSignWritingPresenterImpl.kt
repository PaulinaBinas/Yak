package com.binas.yak.ui.study.sign.learn.writing.presenter

import com.binas.yak.data.model.sign.SignRevisionFlashcard
import com.binas.yak.data.model.studyDay.StudyDay
import com.binas.yak.data.preferences.PreferenceHelper
import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.study.sign.learn.writing.interactor.LearnSignWritingInteractor
import com.binas.yak.ui.study.sign.learn.writing.view.LearnSignWritingView
import com.binas.yak.util.DailyFlashcardQueue
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class LearnSignWritingPresenterImpl<V: LearnSignWritingView, I: LearnSignWritingInteractor>
@Inject internal constructor(interactor: I, var queue: DailyFlashcardQueue, var preferenceHelper: PreferenceHelper): BasePresenter<V, I>(interactor = interactor), LearnSignWritingPresenter<V, I> {

    override fun scheduleReviewCards(id: Long) {
        interactor?.let {
            var timeStudiedMilies = getView()?.getDuration()
            var cards: List<SignRevisionFlashcard> = ArrayList()
            var coroutine = GlobalScope.launch {
                cards = it.getAllMatchingRevisionFlashcards(id)
                updateTimeStudied(it, timeStudiedMilies)
                it.scheduleReviewsOfSign(id)
                var studyDay = it.getStudyDate()
                if(studyDay == null) {
                    studyDay = StudyDay(null)
                }
                studyDay.elementsStudied = studyDay.elementsStudied?.plus(1)
                it.saveStudyDay(studyDay)
                var flashcard = it.getSignStudyFlashcard(id)
                var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
                preferenceHelper.getCurrentUserEmail()?.let { email ->
                    var user = firestore.collection("users").document(email)
                    user.update("studiedFlashcards", FieldValue.arrayUnion(flashcard))
                    val data = hashMapOf(
                        "day" to studyDay.date.toString(),
                        "elementsStudied" to studyDay.elementsStudied
                    )
                    user.collection("studyDays").document(studyDay.date.toString()).set(data)
                }
            }
            while(!coroutine.isCompleted){}
            queue.removeFlashcard()
            for(item in cards) {
                queue.addFlashcard(item)
            }
        }
    }

    private fun updateTimeStudied(it: LearnSignWritingInteractor, time: Long?) {
        var id = preferenceHelper.getCurrentUserId()
        var currentTotal = it.getUserStudyTime(id)
        var totalMinutes = currentTotal + ((time!!.toDouble() / 1000 ) / 60)
        it.setUserStudyTime(id, totalMinutes)
        var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
        preferenceHelper.getCurrentUserEmail()?.let { email ->
            firestore.collection("users").document(email)
                .update("totalMinutesStudied", totalMinutes)
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