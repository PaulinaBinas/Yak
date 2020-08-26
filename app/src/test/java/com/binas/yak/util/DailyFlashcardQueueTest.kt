package com.binas.yak.util

import com.binas.yak.data.model.RevisionType
import com.binas.yak.data.model.grammar.GrammarRevisionFlashcard
import com.binas.yak.data.model.grammar.GrammarStudyFlashcard
import com.binas.yak.data.model.sign.SignRevisionFlashcard
import com.binas.yak.data.model.sign.SignStudyFlashcard
import com.binas.yak.data.model.vocabulary.VocabularyRevisionFlashcard
import com.binas.yak.data.model.vocabulary.VocabularyStudyFlashcard
import com.binas.yak.util.impl.DailyFlashcardQueueImpl
import junit.framework.Assert.assertEquals
import org.junit.Test

class DailyFlashcardQueueTest {

    @Test
    fun containsAddedElements() {
        /* arrange */
        var queue = DailyFlashcardQueueImpl()
        var flashcard1 = SignStudyFlashcard(1L, 0, "", 1L)
        var flashcard2 = VocabularyStudyFlashcard(2L, 1L)
        var flashcard3 = GrammarStudyFlashcard(3L, 1L)
        var flashcard4 = SignRevisionFlashcard(1L, RevisionType.PRONUNCIATION, 2L)
        var flashcard5 = VocabularyRevisionFlashcard(2L, 2L, RevisionType.MEANING)
        var flashcard6 = GrammarRevisionFlashcard(3L, 2L, RevisionType.WRITING)

        /* act */
        queue.addFlashcard(flashcard1)
        queue.addFlashcard(flashcard2)
        queue.addFlashcard(flashcard3)
        queue.addFlashcard(flashcard4)
        queue.addFlashcard(flashcard5)
        queue.addFlashcard(flashcard6)

        /* assert */
        assert(queue.todaysFlashcards.contains(flashcard1))
        assert(queue.todaysFlashcards.contains(flashcard2))
        assert(queue.todaysFlashcards.contains(flashcard3))
        assert(queue.todaysFlashcards.contains(flashcard4))
        assert(queue.todaysFlashcards.contains(flashcard5))
        assert(queue.todaysFlashcards.contains(flashcard6))
    }

    @Test
    fun doesntContainRemovedElements() {
        /* arrange */
        var queue = DailyFlashcardQueueImpl()
        var flashcard1 = SignStudyFlashcard(1L, 0, "", 1L)
        var flashcard2 = VocabularyStudyFlashcard(2L, 1L)
        var flashcard3 = GrammarStudyFlashcard(3L, 1L)
        var flashcard4 = SignRevisionFlashcard(1L, RevisionType.PRONUNCIATION, 2L)
        var flashcard5 = VocabularyRevisionFlashcard(2L, 2L, RevisionType.MEANING)
        var flashcard6 = GrammarRevisionFlashcard(3L, 2L, RevisionType.WRITING)

        /* act */
        queue.addFlashcard(flashcard1)
        queue.addFlashcard(flashcard2)
        queue.addFlashcard(flashcard3)
        queue.addFlashcard(flashcard4)
        queue.addFlashcard(flashcard5)
        queue.addFlashcard(flashcard6)

        queue.removeFlashcard()
        queue.removeFlashcard()
        queue.removeFlashcard()

        /* assert */
        assertEquals(3, queue.todaysFlashcards.size)
        assert(!queue.todaysFlashcards.contains(flashcard1))
        assert(!queue.todaysFlashcards.contains(flashcard2))
        assert(!queue.todaysFlashcards.contains(flashcard3))
        assert(queue.todaysFlashcards.contains(flashcard4))
        assert(queue.todaysFlashcards.contains(flashcard5))
        assert(queue.todaysFlashcards.contains(flashcard6))
    }

    @Test
    fun returnsFlashcardsInCorrectOrder() {
        /* arrange */
        var queue = DailyFlashcardQueueImpl()
        var flashcard1 = SignStudyFlashcard(1L, 0, "", 1L)
        var flashcard2 = VocabularyStudyFlashcard(2L, 1L)
        var flashcard3 = GrammarStudyFlashcard(3L, 1L)
        var flashcard4 = SignRevisionFlashcard(1L, RevisionType.PRONUNCIATION, 2L)
        var flashcard5 = VocabularyRevisionFlashcard(2L, 2L, RevisionType.MEANING)
        var flashcard6 = GrammarRevisionFlashcard(3L, 2L, RevisionType.WRITING)

        /* act */
        queue.addFlashcard(flashcard1)
        queue.addFlashcard(flashcard2)
        queue.addFlashcard(flashcard3)
        queue.addFlashcard(flashcard4)
        queue.addFlashcard(flashcard5)
        queue.addFlashcard(flashcard6)

        /* assert */
        assertEquals(flashcard1, queue.getNextFlashcard())
        queue.removeFlashcard()
        assertEquals(flashcard2, queue.getNextFlashcard())
        queue.removeFlashcard()
        assertEquals(flashcard3, queue.getNextFlashcard())
        queue.removeFlashcard()
        assertEquals(flashcard4, queue.getNextFlashcard())
        queue.removeFlashcard()
        assertEquals(flashcard5, queue.getNextFlashcard())
        queue.removeFlashcard()
        assertEquals(flashcard6, queue.getNextFlashcard())
    }

    @Test
    fun isEmpty() {
        /* arrange */
        var queue = DailyFlashcardQueueImpl()

        /* act */
        queue.addFlashcard(SignStudyFlashcard(1L, 0, "", 1L))
        queue.removeFlashcard()

        /* assert */
        assertEquals(true, queue.isQueueEmpty())
    }

    @Test
    fun isNotEmpty() {
        /* arrange */
        var queue = DailyFlashcardQueueImpl()

        /* act */
        queue.addFlashcard(SignStudyFlashcard(1L, 0, "", 1L))
        queue.addFlashcard(VocabularyStudyFlashcard(1L, 1L))
        queue.addFlashcard(GrammarStudyFlashcard(1L, 1L))
        queue.addFlashcard(SignRevisionFlashcard(1L, RevisionType.PRONUNCIATION, 1L))

        /* assert */
        assertEquals(false, queue.isQueueEmpty())
    }
}