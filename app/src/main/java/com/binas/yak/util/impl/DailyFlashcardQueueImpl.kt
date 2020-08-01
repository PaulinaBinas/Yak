package com.binas.yak.util.impl

import com.binas.yak.data.model.Flashcard
import com.binas.yak.util.DailyFlashcardQueue
import java.util.*

class DailyFlashcardQueueImpl: DailyFlashcardQueue {

    override var todaysFlashcards: LinkedList<Flashcard> = LinkedList<Flashcard>()

    override fun addFlashcard(flashcard: Flashcard) {
        todaysFlashcards.add(flashcard)
    }

    override fun removeFlashcard() {
        if(todaysFlashcards.isNotEmpty()) {
            todaysFlashcards.remove()
        }
    }

    override fun getNextFlashcard(): Flashcard? {

        return if (!isQueueEmpty()) {
            todaysFlashcards.first
        } else {
            null
        }
    }

    override fun isQueueEmpty(): Boolean {
        return todaysFlashcards.isEmpty()
    }

    override fun addFlashcards(cards: List<Flashcard>) {
        todaysFlashcards.addAll(cards)
    }

}