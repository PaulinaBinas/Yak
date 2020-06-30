package com.binas.yak.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "grammarStudyFlashcard", foreignKeys = [(ForeignKey(entity = Grammar::class,
parentColumns = arrayOf("id"),
childColumns = arrayOf("grammarId"),
onDelete = ForeignKey.CASCADE))])
data class GrammarStudyFlashcard (
    @PrimaryKey(autoGenerate = true) var id: Long,
    var grammarId: Long
)