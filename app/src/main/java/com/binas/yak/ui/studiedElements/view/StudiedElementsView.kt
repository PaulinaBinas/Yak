package com.binas.yak.ui.studiedElements.view

import com.binas.yak.data.model.grammar.Grammar
import com.binas.yak.data.model.grammar.GrammarStudyFlashcard
import com.binas.yak.data.model.sign.Sign
import com.binas.yak.data.model.sign.SignStudyFlashcard
import com.binas.yak.data.model.vocabulary.Vocabulary
import com.binas.yak.data.model.vocabulary.VocabularyStudyFlashcard
import com.binas.yak.ui.base.view.BaseView

interface StudiedElementsView: BaseView {

    fun addNewSignElement(card: SignStudyFlashcard, sign: Sign)

    fun addNewVocabularyElement(card: VocabularyStudyFlashcard, vocabulary: Vocabulary)

    fun addNewGrammarElement(card: GrammarStudyFlashcard, grammar: Grammar)
}