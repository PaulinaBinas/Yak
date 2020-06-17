package com.binas.yak.data.model

import com.binas.yak.util.SpacedRepetitionScheduler
import java.time.LocalDate
import javax.inject.Inject

class SignRevisionFlashcard @Inject constructor(val id: Int,
                                                override var revisionType: RevisionType,
                                                val scheduler: SpacedRepetitionScheduler,
                                                override var retention: Double = 0.0,
                                                override var nextDisplayTime: LocalDate? = null,
                                                override var interval: Long = 0L

) : RevisionFlashcard {

    override fun forget() {
        TODO("Not yet implemented")
        this.scheduler.schedule(this, true)
    }

    override fun reviseSuccessfully() {
        TODO("Not yet implemented")
        this.scheduler.schedule(this, false)
    }
}