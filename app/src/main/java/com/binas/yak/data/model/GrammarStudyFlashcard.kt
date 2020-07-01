package com.binas.yak.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "GrammarStudyFlashcard", foreignKeys = [(ForeignKey(entity = Grammar::class,
parentColumns = arrayOf("id"),
childColumns = arrayOf("grammarId")))])
data class GrammarStudyFlashcard (
    @PrimaryKey(autoGenerate = true) var id: Long,
    var grammarId: Long
)