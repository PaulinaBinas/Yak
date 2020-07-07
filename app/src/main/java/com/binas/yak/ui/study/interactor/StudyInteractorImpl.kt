package com.binas.yak.ui.study.interactor

import com.binas.yak.data.model.grammar.GrammarRepository
import com.binas.yak.data.model.grammar.GrammarStudyFlashcard
import com.binas.yak.data.model.sign.SignRepository
import com.binas.yak.data.model.sign.SignStudyFlashcard
import com.binas.yak.data.model.studyOrder.StudyOrder
import com.binas.yak.data.model.studyOrder.StudyOrderRepository
import com.binas.yak.data.model.vocabulary.VocabularyRepository
import com.binas.yak.data.model.vocabulary.VocabularyStudyFlashcard
import javax.inject.Inject

class StudyInteractorImpl @Inject internal constructor(var studyOrderRepo: StudyOrderRepository, var signRepo: SignRepository, var vocabularyRepo: VocabularyRepository, var grammarRepo: GrammarRepository): StudyInteractor {

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
}