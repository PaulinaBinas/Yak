package com.binas.yak.ui.study.interactor

import com.binas.yak.data.model.grammar.GrammarRepository
import com.binas.yak.data.model.grammar.GrammarRevisionFlashcard
import com.binas.yak.data.model.grammar.GrammarStudyFlashcard
import com.binas.yak.data.model.sign.SignRepository
import com.binas.yak.data.model.sign.SignRevisionFlashcard
import com.binas.yak.data.model.sign.SignStudyFlashcard
import com.binas.yak.data.model.studyDay.StudyDay
import com.binas.yak.data.model.studyDay.StudyDayRepository
import com.binas.yak.data.model.studyOrder.StudyOrder
import com.binas.yak.data.model.studyOrder.StudyOrderRepository
import com.binas.yak.data.model.vocabulary.VocabularyRepository
import com.binas.yak.data.model.vocabulary.VocabularyRevisionFlashcard
import com.binas.yak.data.model.vocabulary.VocabularyStudyFlashcard
import java.time.LocalDate
import javax.inject.Inject

class StudyInteractorImpl @Inject internal constructor(var studyDayRepo: StudyDayRepository, var studyOrderRepo: StudyOrderRepository, var signRepo: SignRepository, var vocabularyRepo: VocabularyRepository, var grammarRepo: GrammarRepository): StudyInteractor {

    override fun getStudyOrderList(): List<StudyOrder> {
        return studyOrderRepo.getStudyOrderList()
    }

    override fun getSignStudyCard(id: Long): SignStudyFlashcard {
        return signRepo.getSignStudyFlashcardById(id)
    }

    override fun getVocabularyStudyFlashcard(id: Long): VocabularyStudyFlashcard {
        return vocabularyRepo.getVocabularyStudyFlashcardById(id)
    }

    override fun getGrammarStudyFlashcard(id: Long): GrammarStudyFlashcard {
        return grammarRepo.getGrammarStudyCard(id)
    }

    override fun getScheduledSignRevisionFlashcards(): List<SignRevisionFlashcard> {
        var today = LocalDate.now()
        return signRepo.getScheduledRevisionFlashcards(today)
    }

    override fun getScheduledVocabularyRevisionFlashcards(): List<VocabularyRevisionFlashcard> {
        var today = LocalDate.now()
        return vocabularyRepo.getScheduledRevisionFlashcards(today)
    }

    override fun getScheduledGrammarRevisionFlashcards(): List<GrammarRevisionFlashcard> {
        var today = LocalDate.now()
        return grammarRepo.getScheduledRevisionFlashcards(today)
    }

    override fun getStudyDay(): StudyDay {
        var today = LocalDate.now()
        return studyDayRepo.getStudyDayByDate(today)
    }
}