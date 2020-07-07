package com.binas.yak.data.model.vocabulary

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.binas.yak.data.model.Flashcard
import com.binas.yak.data.model.grammar.GrammarStudyFlashcard
import com.binas.yak.data.model.vocabulary.Vocabulary

@Entity(tableName = "VocabularyStudyFlashcard", foreignKeys = [(ForeignKey(entity = Vocabulary::class,
parentColumns = arrayOf("id"),
childColumns = arrayOf("vocabularyId")))])
data class VocabularyStudyFlashcard (
    @PrimaryKey(autoGenerate = true) var id: Long,
    var vocabularyId: Long
): Flashcard {
    override fun compareTo(other: Flashcard): Int {
        return if(other is VocabularyStudyFlashcard && other.id > this.id) 0 else 1
    }

    var ifStudied: Long = 0
    var chosenPictureId: Long? = null
    var userDescription: String? = null
}