package com.binas.yak.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.binas.yak.util.SpacedRepetitionScheduler
import com.binas.yak.util.impl.SpacedRepetitionSchedulerImpl
import java.time.LocalDate
import javax.inject.Inject

@Entity(tableName = "signRevisionFlashcard", foreignKeys = [(ForeignKey(entity = Sign::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("signId"),
    onDelete = ForeignKey.CASCADE))])
class SignRevisionFlashcard constructor(

    @PrimaryKey(autoGenerate = true) val id: Long,
    override var revisionType: RevisionType,
    var signId: Long
) : RevisionFlashcard {

    @Ignore
    @Inject
    lateinit var scheduler: SpacedRepetitionScheduler
    override var retention: Double = 0.0
    override var nextDisplayTime: LocalDate? = null
    override var interval: Long = 0L

    override fun forget() {
        this.scheduler.schedule(this, false)
    }

    override fun reviseSuccessfully() {
        this.scheduler.schedule(this, true)
    }
}