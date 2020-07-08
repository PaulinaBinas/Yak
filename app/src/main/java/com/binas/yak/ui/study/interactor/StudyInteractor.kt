package com.binas.yak.ui.study.interactor

import com.binas.yak.data.model.grammar.GrammarRevisionFlashcard
import com.binas.yak.data.model.grammar.GrammarStudyFlashcard
import com.binas.yak.data.model.sign.SignRevisionFlashcard
import com.binas.yak.data.model.sign.SignStudyFlashcard
import com.binas.yak.data.model.studyOrder.StudyOrder
import com.binas.yak.data.model.vocabulary.VocabularyRevisionFlashcard
import com.binas.yak.data.model.vocabulary.VocabularyStudyFlashcard
import com.binas.yak.ui.base.interactor.Interactor

interface StudyInteractor: Interactor {

    fun getStudyOrderList(): List<StudyOrder>

    fun getSignStudyCard(id: Long): SignStudyFlashcard

    fun getVocabularyStudyFlashcard(id: Long): VocabularyStudyFlashcard

    fun getGrammarStudyFlashcard(id: Long): GrammarStudyFlashcard

    fun getScheduledSignRevisionFlashcards(): List<SignRevisionFlashcard>

    fun getScheduledVocabularyRevisionFlashcards(): List<VocabularyRevisionFlashcard>

    fun getScheduledGrammarRevisionFlashcards(): List<GrammarRevisionFlashcard>
}