package com.binas.yak.ui.study.grammar.learn.studyCard.interactor

import com.binas.yak.data.model.grammar.Grammar
import com.binas.yak.data.model.grammar.GrammarRepository
import com.binas.yak.data.model.grammar.GrammarStudyFlashcard
import com.binas.yak.data.model.translation.Translation
import com.binas.yak.data.model.translation.TranslationRepository
import javax.inject.Inject

open class GrammarStudyCardInteractorImpl @Inject internal constructor(var grammarRepo: GrammarRepository, var translationRepo: TranslationRepository): GrammarStudyCardInteractor {

    override fun getGrammarStudyCard(id: Long): GrammarStudyFlashcard {
        return grammarRepo.getGrammarStudyCard(id)
    }

    override fun getGrammar(id: Long): Grammar {
        return grammarRepo.getGrammar(id)
    }

    override fun getTranslation(id: Long): Translation {
        return translationRepo.getTranslation(id)
    }
}