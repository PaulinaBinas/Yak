package com.binas.yak.ui.study.common.compareWriting.interactor

import com.binas.yak.data.model.RevisionFlashcard
import com.binas.yak.data.model.grammar.GrammarRepository
import com.binas.yak.data.model.grammar.GrammarRevisionFlashcard
import com.binas.yak.data.model.sign.SignRepository
import com.binas.yak.data.model.sign.SignRevisionFlashcard
import com.binas.yak.data.model.user.UserRepository
import com.binas.yak.data.model.vocabulary.VocabularyRepository
import com.binas.yak.data.model.vocabulary.VocabularyRevisionFlashcard
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

open class CompareWritingInteractorImpl @Inject internal constructor(var userRepo: UserRepository, var signRepo: SignRepository, var vocabularyRepo: VocabularyRepository, var grammarRepo: GrammarRepository): CompareWritingInteractor {

    override fun getCard(id: Long, type: String): RevisionFlashcard? {
        when(type) {
            "sign" -> return signRepo.getSignRevisionFlashcardById(id)
            "vocabulary" -> return vocabularyRepo.getVocabularyRevisionFlashcardById(id)
            "grammar" -> return grammarRepo.getGrammarRevisionCard(id)
        }
        return null
    }

    override fun saveCard(card: RevisionFlashcard, type: String) {
        when(type) {
            "sign" -> signRepo.saveSignRevisionFlashcard(card as SignRevisionFlashcard)
            "vocabulary" -> vocabularyRepo.saveVocabularyRevisionFlashcard(card as VocabularyRevisionFlashcard)
            "grammar" -> grammarRepo.saveGrammarRevisionCard(card as GrammarRevisionFlashcard)
        }
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

    override fun updateRevisedFlashcards(email: String, card: RevisionFlashcard, type: String) {
        var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
        var idNumber = when(type) {
            "sign" -> "1" + card.id.toString()
            "vocabulary" -> "2" + card.id.toString()
            "grammar" -> "3" + card.id.toString()
            else -> card.id.toString()
        }
        firestore.collection("users").document(email)
            .collection("revisedFlashcards").document(idNumber).set(card)
    }
}