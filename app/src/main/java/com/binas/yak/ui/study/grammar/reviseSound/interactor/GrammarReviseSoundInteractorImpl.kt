package com.binas.yak.ui.study.grammar.reviseSound.interactor

import com.binas.yak.data.model.grammar.Grammar
import com.binas.yak.data.model.grammar.GrammarRepository
import com.binas.yak.data.model.grammar.GrammarRevisionFlashcard
import com.binas.yak.data.model.translation.Translation
import com.binas.yak.data.model.translation.TranslationRepository
import javax.inject.Inject

open class GrammarReviseSoundInteractorImpl @Inject internal constructor(var grammarRepo: GrammarRepository, var translationRepo: TranslationRepository): GrammarReviseSoundInteractor {

    override fun getGrammarRevisionFlashcard(id: Long): GrammarRevisionFlashcard {
        return grammarRepo.getGrammarRevisionCard(id)
    }

    override fun getGrammar(id: Long): Grammar {
        return grammarRepo.getGrammar(id)
    }

    override fun getTranslation(id: Long): Translation {
        return translationRepo.getTranslation(id)
    }
}