package com.binas.yak.ui.study.vocabulary.learn.learnWriting.interactor

import com.binas.yak.data.model.vocabulary.VocabularyRepository
import com.binas.yak.data.model.vocabulary.VocabularyRevisionFlashcard
import java.time.LocalDate
import javax.inject.Inject

class LearnVocabularyWritingInteractorImpl @Inject internal constructor(var vocabularyRepo: VocabularyRepository): LearnVocabularyWritingInteractor {

    override fun scheduleReviewsOfVocabulary(id: Long) {
        var today = LocalDate.now()
        vocabularyRepo.scheduleReviewsOfVocabulary(id, today)
    }

    override fun getAllMatchingRevisionFlashcards(id: Long): List<VocabularyRevisionFlashcard> {
        return vocabularyRepo.getRevisionFlashcardsWithVocabularyId(id)
    }

    override fun markCardAsStudied(id: Long) {
        vocabularyRepo.markCardAsStudied(id)
    }
}