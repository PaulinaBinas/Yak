package com.binas.yak.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

@Entity(tableName = "grammar", foreignKeys = [(ForeignKey(entity = Translation::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("translationId"),
    onDelete = ForeignKey.CASCADE))])
data class Grammar (
    @PrimaryKey val id: Long,
    var firstPartOfTibetanSentence: String,
    var grammarPhase: String,
    var secondPartOfTibetanSentence: String,
    var audioFileName: String,
    var translationId: Long
)