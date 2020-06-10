package com.binas.yak.util

import com.binas.yak.data.model.RevisionFlashcard
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.exp

@Singleton
class SpacedRepetitionSchedulerImpl @Inject constructor() : SpacedRepetitionScheduler {
    override fun schedule(flashcard: RevisionFlashcard, remembered: Boolean): LocalDate {

        if (remembered) {
            val oldDisplayTime = flashcard.nextDisplayTime

        } else {
            flashcard.nextDisplayTime = LocalDate.now()
        }
        val interval = flashcard.nextDisplayTime?.until(LocalDate.now(), ChronoUnit.DAYS)
        flashcard.retention = calculateRetention(0, interval)

        TODO("Not yet implemented")
    }

    private fun calculateNextDisplayTime() {
        TODO("Not yet implemented")
    }

    override fun calculateRetention(days: Int, interval: Long?): Double {
        val argument = (days * (-1)) / (4.5 * interval!!)
        return exp(argument)
    }
}