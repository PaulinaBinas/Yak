package com.binas.yak.util.impl

import com.binas.yak.data.model.Flashcard
import com.binas.yak.util.DailyFlashcardQueue
import java.util.*
import javax.inject.Singleton

@Singleton
class DailyFlashcardQueueImpl: DailyFlashcardQueue {

    override var todaysFlashcards: Queue<Flashcard> = PriorityQueue()

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

    override fun addFlashcards(cards: List<Flashcard>) {
        for(item in cards) {
            todaysFlashcards.add(item)
        }
    }
}