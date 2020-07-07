package com.binas.yak.util.impl

import com.binas.yak.data.model.RevisionFlashcard
import com.binas.yak.util.SpacedRepetitionScheduler
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.math.exp

@Singleton
class SpacedRepetitionSchedulerImpl: SpacedRepetitionScheduler {

    private val firstInterval: Long = 1

    private val secondInterval: Long = 2

    override fun schedule(flashcard: RevisionFlashcard, remembered: Boolean) {

        if (remembered) {
            flashcard.nextDisplayTime = calculateNextDisplayTime(flashcard)
        } else {
            flashcard.nextDisplayTime = LocalDate.now()
        }
        flashcard.interval = LocalDate.now().until(flashcard.nextDisplayTime!!, ChronoUnit.DAYS)
        flashcard.retention = calculateRetention(0, flashcard.interval!!)
    }

    override fun calculateRetention(days: Int, interval: Long): Double {
        val argument = (days * (-1)) / (4.5 * interval)
        return exp(argument)
    }

    private fun calculateNextDisplayTime(flashcard: RevisionFlashcard): LocalDate {
        var nextDisplayTime: LocalDate;
        if(flashcard.interval == null) {
            nextDisplayTime = LocalDate.now().plusDays(firstInterval)
        } else if(flashcard.interval == firstInterval) {
            nextDisplayTime = LocalDate.now().plusDays(secondInterval)
        } else {
            nextDisplayTime = LocalDate.now().plusDays(getNextFibonacciNumber(flashcard.interval!!))
        }
        return nextDisplayTime
    }

    private fun getNextFibonacciNumber(number: Long): Long {
        var current: Long = 1
        var next: Long = 2
        while(current != number) {
            next = current + next
            current = next - current
        }
        return next;
    }
}