package com.binas.yak.ui.study.grammar.reviseSound.pronunciationCheck.interactor

import com.binas.yak.data.model.RevisionFlashcard
import com.binas.yak.data.model.grammar.GrammarRepository
import com.binas.yak.data.model.grammar.GrammarRevisionFlashcard
import com.binas.yak.data.model.user.UserRepository
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

open class GrammarPronunciationCheckInteractorImpl @Inject internal constructor(var userRepo: UserRepository, var grammarRepo: GrammarRepository): GrammarPronunciationCheckInteractor {

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

    override fun updateTimeStudied(email: String, time: Double) {
        var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
        firestore.collection("users").document(email)
            .update("totalMinutesStudied", time)
    }

    override fun updateRevisedFlashcards(email: String, card: RevisionFlashcard) {
        var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
        var idNumber = "3" + card.id.toString()
        firestore.collection("users").document(email)
            .collection("revisedFlashcards").document(idNumber).set(card)
    }
}