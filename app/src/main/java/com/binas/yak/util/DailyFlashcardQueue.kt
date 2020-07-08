package com.binas.yak.util

import com.binas.yak.data.model.Flashcard
import java.util.*

interface DailyFlashcardQueue {

    var todaysFlashcards: Queue<Flashcard>

    fun addFlashcard(flashcard: Flashcard)

    fun removeFlashcard()

    fun getNextFlashcard(): Flashcard?

    fun isQueueEmpty(): Boolean

    fun addFlashcards(cards: List<Flashcard>)
}