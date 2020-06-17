package com.binas.yak.util

import com.binas.yak.data.model.RevisionFlashcard
import java.time.LocalDate

interface SpacedRepetitionScheduler {
    fun schedule(flashcard: RevisionFlashcard, remembered: Boolean)

    fun calculateRetention(days: Int, interval: Long): Double
}