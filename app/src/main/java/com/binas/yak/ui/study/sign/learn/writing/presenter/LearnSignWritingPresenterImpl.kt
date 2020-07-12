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
            var cards: List<SignRevisionFlashcard> = ArrayList()
            var coroutine = GlobalScope.launch {
                it.scheduleReviewsOfSign(id)
                cards = it.getAllMatchingRevisionFlashcards(id)
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