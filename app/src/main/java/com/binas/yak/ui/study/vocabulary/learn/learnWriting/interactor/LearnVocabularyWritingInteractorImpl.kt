package com.binas.yak.ui.study.vocabulary.learn.learnWriting.interactor

import com.binas.yak.data.model.studyDay.StudyDay
import com.binas.yak.data.model.studyDay.StudyDayRepository
import com.binas.yak.data.model.vocabulary.VocabularyRepository
import com.binas.yak.data.model.vocabulary.VocabularyRevisionFlashcard
import java.time.LocalDate
import javax.inject.Inject

class LearnVocabularyWritingInteractorImpl @Inject internal constructor(var studyDayRepo: StudyDayRepository ,var vocabularyRepo: VocabularyRepository): LearnVocabularyWritingInteractor {

    override fun scheduleReviewsOfVocabulary(id: Long) {
        var today = LocalDate.now()
        vocabularyRepo.scheduleReviewsOfVocabulary(id, today)
    }

    override fun getAllMatchingRevisionFlashcards(id: Long): List<VocabularyRevisionFlashcard> {
        return vocabularyRepo.getRevisionFlashcardsWithVocabularyId(id)
    }

    override fun markCardAsStudied(id: Long) {
        vocabularyRepo.markCardAsStudied(id)
    }

    override fun getStudyDay(): StudyDay {
        var today = LocalDate.now()
        return studyDayRepo.getStudyDayByDate(today)
    }

    override fun saveStudyDay(day: StudyDay) {
        studyDayRepo.updateStudyDay(day)
    }
}