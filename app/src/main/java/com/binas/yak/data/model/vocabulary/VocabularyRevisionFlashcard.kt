package com.binas.yak.data.model.vocabulary

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.binas.yak.data.model.Flashcard
import com.binas.yak.data.model.RevisionFlashcard
import com.binas.yak.data.model.RevisionType
import com.binas.yak.data.model.grammar.GrammarStudyFlashcard
import com.binas.yak.util.SpacedRepetitionScheduler
import com.google.firebase.firestore.Exclude
import java.time.LocalDate
import javax.inject.Inject

@Entity(tableName = "VocabularyRevisionFlashcard", foreignKeys = [(ForeignKey(entity = Vocabulary::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("vocabularyId")))])
data class VocabularyRevisionFlashcard (
    @PrimaryKey(autoGenerate = true) override var id: Long,
    var vocabularyId: Long,
    override var revisionType: RevisionType
): RevisionFlashcard {

    override var retention: Double? = 0.0
    @set:Exclude @get:Exclude override var nextDisplayTime: LocalDate? = null
    override var interval: Long? = 0L
    var chosenPictureId: Long? = null
    var userDescription: String? = null

}