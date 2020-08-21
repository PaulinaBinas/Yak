package com.binas.yak.util

import com.binas.yak.data.model.RevisionFlashcard
import com.binas.yak.data.model.RevisionType
import com.binas.yak.data.model.sign.SignRevisionFlashcard
import com.binas.yak.util.impl.SpacedRepetitionSchedulerImpl
import junit.framework.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal
import java.time.LocalDate

class SpacedRepetitionSchedulerTest {

    @Test
    fun scheduleNewlyStudiedCardTest() {
        /* arrange */
        val scheduler: SpacedRepetitionScheduler =
            SpacedRepetitionSchedulerImpl()
        var revisionFlashcard = SignRevisionFlashcard(1, RevisionType.PRONUNCIATION, 1)

        /* act */
        scheduler.schedule(revisionFlashcard, false)

        /* assert */
        assertEquals(revisionFlashcard.nextDisplayTime, LocalDate.now())
        assertEquals(revisionFlashcard.retention, 0.0)
    }

    @Test
    fun calculateRetentionAfterADay() {
        /* arrange */
        val scheduler: SpacedRepetitionScheduler =
            SpacedRepetitionSchedulerImpl()
        val interval = 1L
        val days = 1

        /* act */
        var retention = scheduler.calculateRetention(days, interval)

        /* assert */
        retention = BigDecimal(retention).setScale(2, BigDecimal.ROUND_HALF_UP).toDouble()
        assertEquals(retention, 0.8)

    }

    @Test
    fun calculateRetentionOfOutdatedCard() {
        /* arrange */
        val scheduler: SpacedRepetitionScheduler =
            SpacedRepetitionSchedulerImpl()
        val interval = 1L
        val days = 3

        /* act */
        var retention = scheduler.calculateRetention(days, interval)

        /* assert */
        retention = BigDecimal(retention).setScale(2, BigDecimal.ROUND_HALF_UP).toDouble()
        assertEquals(retention, 0.51)

    }

    @Test
    fun calculateRetentionOfElementRememberedAfter21Days() {
        /* arrange */
        val scheduler: SpacedRepetitionScheduler =
            SpacedRepetitionSchedulerImpl()
        val flashcard: RevisionFlashcard =
            SignRevisionFlashcard(
                11,
                RevisionType.MEANING,
                1
            )
        flashcard.interval = 21
        flashcard.nextDisplayTime = LocalDate.now()

        /* act */
        scheduler.schedule(flashcard, true)

        /* assert */
        assertEquals(flashcard.interval!!, 34L)
        assertEquals(flashcard.nextDisplayTime, LocalDate.now().plusDays(34
        ))
    }
}