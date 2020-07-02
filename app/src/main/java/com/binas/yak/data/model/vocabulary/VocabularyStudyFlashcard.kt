package com.binas.yak.data.model.vocabulary

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.binas.yak.data.model.vocabulary.Vocabulary

@Entity(tableName = "VocabularyStudyFlashcard", foreignKeys = [(ForeignKey(entity = Vocabulary::class,
parentColumns = arrayOf("id"),
childColumns = arrayOf("vocabularyId")))])
data class VocabularyStudyFlashcard (
    @PrimaryKey(autoGenerate = true) var id: Long,
    var vocabularyId: Long
) {
    var ifStudied: Long = 0
    var chosenPictureId: Long? = null
    var userDescription: String? = null
}