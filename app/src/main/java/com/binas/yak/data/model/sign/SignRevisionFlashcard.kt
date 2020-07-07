package com.binas.yak.data.model.sign

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.binas.yak.data.model.Flashcard
import com.binas.yak.data.model.RevisionFlashcard
import com.binas.yak.data.model.RevisionType
import com.binas.yak.data.model.grammar.GrammarStudyFlashcard
import com.binas.yak.util.SpacedRepetitionScheduler
import java.time.LocalDate
import javax.inject.Inject

@Entity(tableName = "SignRevisionFlashcard", foreignKeys = [(ForeignKey(entity = Sign::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("signId")))])
class SignRevisionFlashcard constructor(
    @PrimaryKey(autoGenerate = true) val id: Long,
    override var revisionType: RevisionType,
    var signId: Long
) : RevisionFlashcard {

    @Ignore
    @Inject
    lateinit var scheduler: SpacedRepetitionScheduler
    override var retention: Double? = 0.0
    override var nextDisplayTime: LocalDate? = null
    override var interval: Long? = 0L
    var userDescription: String? = null
    var comparisonSignId: Long? = null

    override fun forget() {
        this.scheduler.schedule(this, false)
    }

    override fun reviseSuccessfully() {
        this.scheduler.schedule(this, true)
    }

    override fun compareTo(other: Flashcard): Int {
        return if(other is SignRevisionFlashcard && other.id > this.id) 0 else 1
    }
}