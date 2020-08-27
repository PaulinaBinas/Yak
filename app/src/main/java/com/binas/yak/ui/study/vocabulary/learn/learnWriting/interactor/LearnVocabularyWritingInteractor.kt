package com.binas.yak.ui.study.vocabulary.learn.learnWriting.interactor

import com.binas.yak.data.model.studyDay.StudyDay
import com.binas.yak.data.model.vocabulary.VocabularyRevisionFlashcard
import com.binas.yak.data.model.vocabulary.VocabularyStudyFlashcard
import com.binas.yak.ui.base.interactor.Interactor

interface LearnVocabularyWritingInteractor: Interactor {

    fun scheduleReviewsOfVocabulary(id: Long)

    fun getAllMatchingRevisionFlashcards(id: Long): List<VocabularyRevisionFlashcard>

    fun markCardAsStudied(id: Long)

    fun getStudyDay(): StudyDay

    fun saveStudyDay(day: StudyDay)

    fun getVocabularyStudyFlashcard(id: Long): VocabularyStudyFlashcard

    fun getUserStudyTime(id: Long): Double

    fun setUserStudyTime(id: Long, time: Double)

    fun updateTotalTime(email: String, time: Double)

    fun updateElementsStudied(email: String, flashcard: VocabularyStudyFlashcard, studyDay: StudyDay)
}