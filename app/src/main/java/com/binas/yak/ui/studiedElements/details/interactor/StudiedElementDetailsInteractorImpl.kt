package com.binas.yak.ui.studiedElements.details.interactor

import com.binas.yak.data.model.grammar.GrammarRepository
import com.binas.yak.data.model.grammar.GrammarRevisionFlashcard
import com.binas.yak.data.model.sign.SignRepository
import com.binas.yak.data.model.sign.SignRevisionFlashcard
import com.binas.yak.data.model.translation.Translation
import com.binas.yak.data.model.translation.TranslationRepository
import com.binas.yak.data.model.vocabulary.VocabularyRepository
import com.binas.yak.data.model.vocabulary.VocabularyRevisionFlashcard
import javax.inject.Inject

class StudiedElementDetailsInteractorImpl @Inject internal constructor(var translationRepo: TranslationRepository, var signRepo: SignRepository, var vocabularyRepo: VocabularyRepository, var grammarRepo: GrammarRepository): StudiedElementDetailsInteractor {

    override fun getTranslation(id: Long): Translation {
        return translationRepo.getTranslation(id)
    }

    override fun getSignRevisionCards(id: Long): List<SignRevisionFlashcard> {
        return signRepo.getRevisionFlashcardsWithSignId(id)
    }

    override fun getVocabularyRevisionCards(id: Long): List<VocabularyRevisionFlashcard> {
        return vocabularyRepo.getRevisionFlashcardsWithVocabularyId(id)
    }

    override fun getGrammarRevisionCards(id: Long): List<GrammarRevisionFlashcard> {
        return grammarRepo.getRevisionFlashcardsWithGrammarId(id)
    }
}