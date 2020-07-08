package com.binas.yak.data.model.vocabulary

import java.time.LocalDate
import javax.inject.Inject

class VocabularyRepositoryImpl @Inject internal constructor(var vocabularyDao: VocabularyDao): VocabularyRepository {

    override fun getVocabularyById(id: Long): Vocabulary {
        return vocabularyDao.getVocabularyById(id)
    }

    override fun getVocabularyStudyFlashcardById(id: Long): VocabularyStudyFlashcard {
        return vocabularyDao.getStudyFlashcardById(id)
    }

    override fun getVocabularyRevisionFlashcardById(id: Long): VocabularyRevisionFlashcard {
        return vocabularyDao.getRevisionFlashcardById(id)
    }

    override fun getAllStudiedCards(): List<VocabularyStudyFlashcard> {
        return vocabularyDao.getAllStudiedVocabularyFlashcards()
    }

    override fun getRevisionFlashcardsWithVocabularyId(id: Long): List<VocabularyRevisionFlashcard> {
        return vocabularyDao.getVocabularyRevisionFlashcardsWithVocabularyId(id)
    }

    override fun getScheduledRevisionFlashcards(today: LocalDate): List<VocabularyRevisionFlashcard> {
        return vocabularyDao.getScheduledGrammarRevisionFlashcards(today)
    }
}