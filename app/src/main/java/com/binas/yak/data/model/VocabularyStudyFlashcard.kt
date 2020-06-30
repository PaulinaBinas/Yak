package com.binas.yak.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "vocabularyStudyFlashcard", foreignKeys = [(ForeignKey(entity = Vocabulary::class,
parentColumns = arrayOf("id"),
childColumns = arrayOf("vocabularyId"),
onDelete = ForeignKey.CASCADE))])
data class VocabularyStudyFlashcard (
    @PrimaryKey(autoGenerate = true) var id: Long,
    var vocabularyId: Long
)