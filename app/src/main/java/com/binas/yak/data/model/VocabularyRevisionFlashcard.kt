package com.binas.yak.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.binas.yak.util.SpacedRepetitionScheduler
import java.time.LocalDate
import javax.inject.Inject

@Entity(tableName = "VocabularyRevisionFlashcard", foreignKeys = [(ForeignKey(entity = Vocabulary::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("vocabularyId")))])
data class VocabularyRevisionFlashcard (
    @PrimaryKey(autoGenerate = true) var id: Long,
    var vocabularyId: Long,
    override var revisionType: RevisionType
): RevisionFlashcard {

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