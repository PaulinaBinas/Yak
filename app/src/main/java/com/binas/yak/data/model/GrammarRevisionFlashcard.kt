package com.binas.yak.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.binas.yak.util.SpacedRepetitionScheduler
import java.time.LocalDate
import javax.inject.Inject

@Entity(tableName = "GrammarRevisionFlashcard", foreignKeys = [(ForeignKey(entity = Grammar::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("grammarId")))])
data class GrammarRevisionFlashcard constructor(

    @PrimaryKey(autoGenerate = true) val id: Long,
    var grammarId: Long,
    override var revisionType: RevisionType
) : RevisionFlashcard {

    @Ignore
    @Inject
    lateinit var scheduler: SpacedRepetitionScheduler
    override var retention: Double = 0.0
    override var nextDisplayTime: LocalDate? = null
    override var interval: Long = 0L
    var userDescription: String? = null

    override fun forget() {
        this.scheduler.schedule(this, false)
    }

    override fun reviseSuccessfully() {
        this.scheduler.schedule(this, true)
    }
}