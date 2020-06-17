package com.binas.yak.util

import com.binas.yak.util.impl.SpacedRepetitionSchedulerImpl
import junit.framework.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal

class SpacedRepetitionSchedulerTest {

    @Test
    fun scheduleNewlyStudiedCardTest() {
        /* arrange */
        val scheduler: SpacedRepetitionScheduler =
            SpacedRepetitionSchedulerImpl()

        /* act */

        /* assert */

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
}