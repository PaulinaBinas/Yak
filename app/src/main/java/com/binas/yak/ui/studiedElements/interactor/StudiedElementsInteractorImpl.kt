package com.binas.yak.ui.studiedElements.interactor

import com.binas.yak.data.model.grammar.Grammar
import com.binas.yak.data.model.grammar.GrammarRepository
import com.binas.yak.data.model.grammar.GrammarStudyFlashcard
import com.binas.yak.data.model.sign.Sign
import com.binas.yak.data.model.sign.SignRepository
import com.binas.yak.data.model.sign.SignStudyFlashcard
import com.binas.yak.data.model.translation.Translation
import com.binas.yak.data.model.translation.TranslationRepository
import com.binas.yak.data.model.vocabulary.Vocabulary
import com.binas.yak.data.model.vocabulary.VocabularyRepository
import com.binas.yak.data.model.vocabulary.VocabularyStudyFlashcard
import javax.inject.Inject

class StudiedElementsInteractorImpl @Inject internal constructor(
    var signRepo: SignRepository,
    var vocabularyRepo: VocabularyRepository,
    var grammarRepo: GrammarRepository,
    var translationRepo: TranslationRepository
): StudiedElementsInteractor {

    override fun getLearntSignCards(): List<SignStudyFlashcard> {
        return signRepo.getAllStudiedCards()
    }

    override fun getLearntVocabularyCards(): List<VocabularyStudyFlashcard> {
        return vocabularyRepo.getAllStudiedCards()
    }

    override fun getLearntGrammarCards(): List<GrammarStudyFlashcard> {
        return grammarRepo.getAllStudiedGrammarStudyFlashcards()
    }

    override fun getSign(id: Long): Sign {
        return signRepo.getSignByFlashcardId(id)
    }

    override fun getVocabulary(id: Long): Vocabulary {
        return vocabularyRepo.getVocabularyById(id)
    }

    override fun getGrammar(id: Long): Grammar {
        return grammarRepo.getGrammar(id)
    }

    override fun getTranslation(id: Long): Translation {
        return translationRepo.getTranslation(id)
    }
}