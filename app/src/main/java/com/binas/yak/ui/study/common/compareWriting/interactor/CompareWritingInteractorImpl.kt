package com.binas.yak.ui.study.common.compareWriting.interactor

import com.binas.yak.data.model.RevisionFlashcard
import com.binas.yak.data.model.grammar.GrammarRepository
import com.binas.yak.data.model.grammar.GrammarRevisionFlashcard
import com.binas.yak.data.model.sign.SignRepository
import com.binas.yak.data.model.sign.SignRevisionFlashcard
import com.binas.yak.data.model.vocabulary.VocabularyRepository
import com.binas.yak.data.model.vocabulary.VocabularyRevisionFlashcard
import javax.inject.Inject
import kotlin.math.sign

class CompareWritingInteractorImpl @Inject internal constructor(var signRepo: SignRepository, var vocabularyRepo: VocabularyRepository, var grammarRepo: GrammarRepository): CompareWritingInteractor {

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

}