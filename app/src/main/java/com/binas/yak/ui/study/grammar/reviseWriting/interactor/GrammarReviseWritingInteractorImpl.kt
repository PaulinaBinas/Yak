package com.binas.yak.ui.study.grammar.reviseWriting.interactor

import com.binas.yak.data.model.grammar.Grammar
import com.binas.yak.data.model.grammar.GrammarRepository
import com.binas.yak.data.model.grammar.GrammarRevisionFlashcard
import com.binas.yak.data.preferences.PreferenceHelper
import com.binas.yak.ui.base.interactor.BaseInteractor
import javax.inject.Inject

class GrammarReviseWritingInteractorImpl @Inject internal constructor(preferenceHelper: PreferenceHelper, var grammarRepo: GrammarRepository):
    BaseInteractor(preferenceHelper), GrammarReviseWritingInteractor {

    override fun getGrammarRevisionFlashcard(id: Long): GrammarRevisionFlashcard {
        return grammarRepo.getGrammarRevisionCard(id)
    }

    override fun getGrammar(id: Long): Grammar {
        return grammarRepo.getGrammar(id)
    }
}