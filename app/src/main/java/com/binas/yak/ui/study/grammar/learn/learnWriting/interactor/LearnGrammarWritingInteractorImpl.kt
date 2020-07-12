package com.binas.yak.ui.study.grammar.learn.learnWriting.interactor

import com.binas.yak.data.model.grammar.GrammarRepository
import com.binas.yak.data.model.grammar.GrammarRevisionFlashcard
import com.binas.yak.data.model.grammar.GrammarStudyFlashcard
import com.binas.yak.data.model.studyDay.StudyDay
import com.binas.yak.data.model.studyDay.StudyDayRepository
import java.time.LocalDate
import javax.inject.Inject

class LearnGrammarWritingInteractorImpl @Inject internal constructor(var studyDayRepo: StudyDayRepository, var grammarRepo: GrammarRepository): LearnGrammarWritingInteractor {
    override fun scheduleReviewsOfGrammar(id: Long) {
        var today = LocalDate.now()
        grammarRepo.scheduleReviewsOfGrammar(id, today)
    }

    override fun markCardAsStudied(id: Long) {
        grammarRepo.markCardWithMatchingGrammarIdAsStudied(id)
    }

    override fun getAllMatchingRevisionFlashcards(id: Long): List<GrammarRevisionFlashcard> {
        return grammarRepo.getRevisionFlashcardsWithGrammarId(id)
    }

    override fun getStudyDay(): StudyDay {
        var today = LocalDate.now()
        return studyDayRepo.getStudyDayByDate(today)
    }

    override fun saveStudyDay(day: StudyDay) {
        studyDayRepo.updateStudyDay(day)
    }

    override fun getGrammarStudyFlashcard(id: Long): GrammarStudyFlashcard {
        return grammarRepo.getGrammarStudyCard(id)
    }
}