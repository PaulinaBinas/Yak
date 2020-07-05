package com.binas.yak.data.model.sign


interface SignRepository {

    fun getSignStudyFlashcards(): List<SignStudyFlashcard>
    fun getSignStudyFlashcardById(id: Long): SignStudyFlashcard
    fun getSignRevisionFlashcardById(id: Long): SignRevisionFlashcard
    fun getSignByFlashcardId(id: Long): Sign
    fun addSignStudyFlashcards(flashcards: List<SignStudyFlashcard>)
    fun getAllStudiedCards(): List<SignStudyFlashcard>
}