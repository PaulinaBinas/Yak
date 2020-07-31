package com.binas.yak.ui.study.vocabulary.learn.learnWriting.presenter

import com.binas.yak.data.model.studyDay.StudyDay
import com.binas.yak.data.model.vocabulary.VocabularyRevisionFlashcard
import com.binas.yak.data.preferences.PreferenceHelper
import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.study.vocabulary.learn.learnWriting.interactor.LearnVocabularyWritingInteractor
import com.binas.yak.ui.study.vocabulary.learn.learnWriting.view.LearnVocabularyWritingView
import com.binas.yak.util.DailyFlashcardQueue
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class LearnVocabularyWritingPresenterImpl<V: LearnVocabularyWritingView, I: LearnVocabularyWritingInteractor>
@Inject internal constructor(interactor: I, var queue: DailyFlashcardQueue, var preferenceHelper: PreferenceHelper): BasePresenter<V, I>(interactor = interactor), LearnVocabularyWritingPresenter<V, I> {

    override fun scheduleReviewCards(id: Long) {
        interactor?.let {
            var timeStudiedMilies = getView()?.getDuration()
            var cards: List<VocabularyRevisionFlashcard> = ArrayList()
            var coroutine = GlobalScope.launch {
                it.scheduleReviewsOfVocabulary(id)
                cards = it.getAllMatchingRevisionFlashcards(id)
                GlobalScope.launch {
                    updateTimeStudied(it, timeStudiedMilies)
                    var studyDay = it.getStudyDay()
                    if(studyDay == null) {
                        studyDay = StudyDay(null)
                    }
                    studyDay.elementsStudied = studyDay.elementsStudied?.plus(1)
                    it.saveStudyDay(studyDay)
                    var flashcard = it.getVocabularyStudyFlashcard(id)
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
            }
            while(!coroutine.isCompleted){}
            queue.removeFlashcard()
            for(item in cards) {
                queue.addFlashcard(item)
            }
        }
    }

    private fun updateTimeStudied(it: LearnVocabularyWritingInteractor, time: Long?) {
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