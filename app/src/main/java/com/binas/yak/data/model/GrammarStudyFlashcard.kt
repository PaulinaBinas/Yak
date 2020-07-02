package com.binas.yak.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "GrammarStudyFlashcard", foreignKeys = [(ForeignKey(entity = Grammar::class,
parentColumns = arrayOf("id"),
childColumns = arrayOf("grammarId"))), (ForeignKey(entity = Vocabulary::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("chosenVocabularyId")))])
data class GrammarStudyFlashcard (
    @PrimaryKey(autoGenerate = true) var id: Long,
    var grammarId: Long
) {
    var ifStudied: Long = 0
    var userDescription: String? = null
    var chosenVocabularyId: Long? = null
}
