package com.binas.yak.data.model.sign

import androidx.lifecycle.LiveData

interface SignRepo {

    fun getSignStudyFlashcards(): LiveData<List<SignStudyFlashcard>>
    fun addSignStudyFlashcards(flashcards: List<SignStudyFlashcard>)
}