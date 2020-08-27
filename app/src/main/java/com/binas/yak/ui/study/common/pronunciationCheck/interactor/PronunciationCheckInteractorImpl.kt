package com.binas.yak.ui.study.common.pronunciationCheck.interactor

import com.binas.yak.data.model.RevisionFlashcard
import com.binas.yak.data.model.sign.SignRepository
import com.binas.yak.data.model.sign.SignRevisionFlashcard
import com.binas.yak.data.model.user.UserRepository
import com.binas.yak.data.model.vocabulary.VocabularyRepository
import com.binas.yak.data.model.vocabulary.VocabularyRevisionFlashcard
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

open class PronunciationCheckInteractorImpl @Inject internal constructor(var userRepo: UserRepository, var signRepo: SignRepository, var vocabularyRepo: VocabularyRepository): PronunciationCheckInteractor {

    override fun getCard(id: Long, type: String): RevisionFlashcard? {
        when(type) {
            "sign" -> return signRepo.getSignRevisionFlashcardById(id)
            "vocabulary" -> return vocabularyRepo.getVocabularyRevisionFlashcardById(id)
        }
        return null
    }

    override fun saveCard(card: RevisionFlashcard, type: String) {
        when(type) {
            "sign" -> return signRepo.saveSignRevisionFlashcard(card as SignRevisionFlashcard)
            "vocabulary" -> return vocabularyRepo.saveVocabularyRevisionFlashcard(card as VocabularyRevisionFlashcard)
        }
    }

    override fun getUserStudyTime(id: Long): Double {
        return userRepo.getTotalMinutesStudiedByUserId(id)
    }

    override fun setUserStudyTime(id: Long, time: Double) {
        userRepo.setTotalMinutesStudiedByUserId(id, time)
    }

    override fun updateTotalMinutes(email: String, time: Double) {
        var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
        firestore.collection("users").document(email)
            .update("totalMinutesStudied", time)
    }

    override fun updateRevisedFlashcards(email: String, id: String, flashcard: RevisionFlashcard) {
        var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
        firestore.collection("users").document(email)
            .collection("revisedFlashcards").document(id).set(flashcard)
    }
}