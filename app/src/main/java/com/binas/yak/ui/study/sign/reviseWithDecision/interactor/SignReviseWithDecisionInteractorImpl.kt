package com.binas.yak.ui.study.sign.reviseWithDecision.interactor

import com.binas.yak.data.model.RevisionFlashcard
import com.binas.yak.data.model.sign.Sign
import com.binas.yak.data.model.sign.SignRepository
import com.binas.yak.data.model.sign.SignRevisionFlashcard
import com.binas.yak.data.model.user.UserRepository
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

open class SignReviseWithDecisionInteractorImpl @Inject internal constructor(var userRepo: UserRepository, var signRepo: SignRepository): SignReviseWithDecisionInteractor {

    override fun getSignRevisionFlashcard(id: Long): SignRevisionFlashcard {
        return signRepo.getSignRevisionFlashcardById(id)
    }

    override fun getSign(id: Long): Sign {
        return signRepo.getSignByFlashcardId(id)
    }

    override fun saveCard(card: SignRevisionFlashcard) {
        signRepo.saveSignRevisionFlashcard(card)
    }

    override fun getUserStudyTime(id: Long): Double {
        return userRepo.getTotalMinutesStudiedByUserId(id)
    }

    override fun setUserStudyTime(id: Long, time: Double) {
        userRepo.setTotalMinutesStudiedByUserId(id, time)
    }

    override fun updateTotalTime(email: String, totalMinutes: Double) {
        var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
        firestore.collection("users").document(email)
            .update("totalMinutesStudied", totalMinutes)
    }

    override fun updateReviseFlashcards(email: String, id: String, card: RevisionFlashcard) {
        var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
        firestore.collection("users").document(email)
            .collection("revisedFlashcards").document(id).set(card)
    }
}