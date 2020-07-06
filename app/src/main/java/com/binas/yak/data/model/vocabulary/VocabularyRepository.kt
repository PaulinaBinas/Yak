package com.binas.yak.data.model.vocabulary

interface VocabularyRepository {

    fun getVocabularyById(id: Long): Vocabulary
    fun getVocabularyStudyFlashcardById(id: Long): VocabularyStudyFlashcard
    fun getVocabularyRevisionFlashcardById(id: Long): VocabularyRevisionFlashcard
    fun getAllStudiedCards(): List<VocabularyStudyFlashcard>
    fun getRevisionFlashcardsWithVocabularyId(id: Long): List<VocabularyRevisionFlashcard>
}