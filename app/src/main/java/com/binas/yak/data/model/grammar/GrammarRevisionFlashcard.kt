package com.binas.yak.data.model.grammar

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.binas.yak.data.model.Flashcard
import com.binas.yak.data.model.RevisionFlashcard
import com.binas.yak.data.model.RevisionType
import com.binas.yak.util.SpacedRepetitionScheduler
import com.google.firebase.firestore.Exclude
import java.time.LocalDate
import javax.inject.Inject

@Entity(tableName = "GrammarRevisionFlashcard", foreignKeys = [(ForeignKey(entity = Grammar::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("grammarId")))])
data class GrammarRevisionFlashcard constructor(

    @PrimaryKey(autoGenerate = true) override val id: Long,
    var grammarId: Long,
    override var revisionType: RevisionType
) : RevisionFlashcard {

    override var retention: Double? = 0.0
    @set:Exclude @get:Exclude override var nextDisplayTime: LocalDate? = null
    override var interval: Long? = 0L
    var chosenVocabularyId: Long? = null
    var userDescription: String? = null

    override fun compareTo(other: Flashcard): Int {
        return if(other is GrammarRevisionFlashcard && other.id > this.id) 0 else 1
    }
}