package com.binas.yak.ui.study.grammar.reviseSound.pronunciationCheck.interactor

import com.binas.yak.data.model.grammar.GrammarRepository
import com.binas.yak.data.model.grammar.GrammarRevisionFlashcard
import com.binas.yak.data.model.user.UserRepository
import javax.inject.Inject

class GrammarPronunciationCheckInteractorImpl @Inject internal constructor(var userRepo: UserRepository, var grammarRepo: GrammarRepository): GrammarPronunciationCheckInteractor {

    override fun getCard(id: Long): GrammarRevisionFlashcard {
        return grammarRepo.getGrammarRevisionCard(id)
    }

    override fun saveCard(card: GrammarRevisionFlashcard) {
        grammarRepo.saveGrammarRevisionCard(card)
    }

    override fun getUserStudyTime(id: Long): Double {
        return userRepo.getTotalMinutesStudiedByUserId(id)
    }

    override fun setUserStudyTime(id: Long, time: Double) {
        userRepo.setTotalMinutesStudiedByUserId(id, time)
    }
}