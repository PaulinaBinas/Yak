package com.binas.yak.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

@Entity(tableName = "Grammar", foreignKeys = [(ForeignKey(entity = Translation::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("translationId"),
    onDelete = ForeignKey.CASCADE))])
data class Grammar (
    @PrimaryKey val id: Long,
    var firstPartOfSentence: String,
    var grammarPhase: String,
    var secondPartOfSentence: String,
    var audioFileName: String,
    var translationId: Long
)