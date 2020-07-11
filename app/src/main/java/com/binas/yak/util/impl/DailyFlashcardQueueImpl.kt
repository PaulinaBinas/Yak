package com.binas.yak.util.impl

import com.binas.yak.data.model.Flashcard
import com.binas.yak.data.model.RevisionFlashcard
import com.binas.yak.util.DailyFlashcardQueue
import com.binas.yak.util.UtilModule
import dagger.Component
import java.util.*
import javax.inject.Singleton

class DailyFlashcardQueueImpl: DailyFlashcardQueue {

    override var todaysFlashcards: Queue<Flashcard> = PriorityQueue()

    override fun addFlashcard(flashcard: Flashcard) {
        todaysFlashcards.add(flashcard)
    }

    override fun removeFlashcard() {
        todaysFlashcards.poll()
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

    override fun sort() {
        var sortedQueue = PriorityQueue<Flashcard>()
        var revisionFlashcardsList = ArrayList<RevisionFlashcard>()
        for(item in todaysFlashcards) {
            if(item !is RevisionFlashcard) {
                sortedQueue.add(item)
            } else {
                revisionFlashcardsList.add(item)
            }
        }
        todaysFlashcards = sortedQueue
        this.addFlashcards(revisionFlashcardsList)
    }
}