package com.binas.yak.ui.study.grammar.learn.learnWriting.interactor

import com.binas.yak.data.model.grammar.GrammarRepository
import com.binas.yak.data.model.grammar.GrammarRevisionFlashcard
import java.time.LocalDate
import javax.inject.Inject

class LearnGrammarWritingInteractorImpl @Inject internal constructor(var grammarRepo: GrammarRepository): LearnGrammarWritingInteractor {
    override fun scheduleReviewsOfGrammar(id: Long) {
        var today = LocalDate.now()
        grammarRepo.scheduleReviewsOfGrammar(id, today)
    }

    override fun markCardAsStudied(id: Long) {
        grammarRepo.markCardWithMatchingGrammarIdAsStudied(id)
    }

    override fun getAllMatchingRevisionFlashcards(id: Long): List<GrammarRevisionFlashcard> {
        return grammarRepo.getRevisionFlashcardsWithGrammarId(id)
    }
}