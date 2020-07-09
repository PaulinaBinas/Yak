package com.binas.yak.ui.study.grammar.reviseSound.pronunciationCheck.interactor

import com.binas.yak.data.model.grammar.GrammarRevisionFlashcard
import com.binas.yak.ui.base.interactor.Interactor

interface GrammarPronunciationCheckInteractor: Interactor {

    fun getCard(id: Long): GrammarRevisionFlashcard

    fun saveCard(card: GrammarRevisionFlashcard)
}