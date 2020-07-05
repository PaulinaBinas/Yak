package com.binas.yak.ui.studiedElements.interactor

import com.binas.yak.data.model.grammar.Grammar
import com.binas.yak.data.model.grammar.GrammarStudyFlashcard
import com.binas.yak.data.model.sign.Sign
import com.binas.yak.data.model.sign.SignStudyFlashcard
import com.binas.yak.data.model.translation.Translation
import com.binas.yak.data.model.vocabulary.Vocabulary
import com.binas.yak.data.model.vocabulary.VocabularyStudyFlashcard
import com.binas.yak.ui.base.interactor.Interactor

interface StudiedElementsInteractor: Interactor {

    fun getLearntSignCards(): List<SignStudyFlashcard>

    fun getLearntVocabularyCards(): List<VocabularyStudyFlashcard>

    fun getLearntGrammarCards(): List<GrammarStudyFlashcard>

    fun getSign(id: Long): Sign

    fun getVocabulary(id: Long): Vocabulary

    fun getGrammar(id: Long): Grammar

    fun getTranslation(id: Long): Translation
}