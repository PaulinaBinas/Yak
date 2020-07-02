package com.binas.yak.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.binas.yak.data.model.translation.Translation

@Entity(tableName = "Grammar", foreignKeys = [(ForeignKey(entity = Translation::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("translationId")))])
data class Grammar (
    @PrimaryKey val id: Long,
    var firstPartOfSentence: String?,
    var grammarPhase: String?,
    var secondPartOfSentence: String?,
    var audioFileName: String?,
    var translationId: Long?
)