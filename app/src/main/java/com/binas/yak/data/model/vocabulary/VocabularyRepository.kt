package com.binas.yak.data.model.vocabulary

import java.time.LocalDate

interface VocabularyRepository {

    fun getVocabularyById(id: Long): Vocabulary
    fun getVocabularyStudyFlashcardById(id: Long): VocabularyStudyFlashcard
    fun getVocabularyRevisionFlashcardById(id: Long): VocabularyRevisionFlashcard
    fun getAllStudiedCards(): List<VocabularyStudyFlashcard>
    fun getRevisionFlashcardsWithVocabularyId(id: Long): List<VocabularyRevisionFlashcard>
    fun getScheduledRevisionFlashcards(today: LocalDate): List<VocabularyRevisionFlashcard>
}