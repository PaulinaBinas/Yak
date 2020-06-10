package com.binas.yak.util

import com.binas.yak.data.model.Flashcard
import java.util.*

class DailyFlashcardQueueImpl(override var todaysFlashcards: Queue<Flashcard>) : DailyFlashcardQueue {

    override fun addFlashcard(flashcard: Flashcard) {
        todaysFlashcards.add(flashcard)
    }

    override fun removeFlashcard() {
        todaysFlashcards.remove()
    }

    override fun getNextFlashcard(): Flashcard? {

        if (!isQueueEmpty()) {
            return todaysFlashcards.element()
        } else {
            return null
        }
    }

    override fun isQueueEmpty(): Boolean {
        return todaysFlashcards.isEmpty()
    }
}