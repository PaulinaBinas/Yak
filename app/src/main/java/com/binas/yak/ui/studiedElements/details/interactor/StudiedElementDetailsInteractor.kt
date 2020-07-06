package com.binas.yak.ui.studiedElements.details.interactor

import com.binas.yak.data.model.grammar.GrammarRevisionFlashcard
import com.binas.yak.data.model.sign.SignRevisionFlashcard
import com.binas.yak.data.model.translation.Translation
import com.binas.yak.data.model.vocabulary.VocabularyRevisionFlashcard
import com.binas.yak.ui.base.interactor.Interactor

interface StudiedElementDetailsInteractor: Interactor {

    fun getTranslation(id: Long): Translation

    fun getSignRevisionCards(id: Long): List<SignRevisionFlashcard>

    fun getVocabularyRevisionCards(id: Long): List<VocabularyRevisionFlashcard>

    fun getGrammarRevisionCards(id: Long): List<GrammarRevisionFlashcard>
}