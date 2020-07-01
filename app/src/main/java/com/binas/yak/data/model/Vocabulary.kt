package com.binas.yak.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

@Entity(tableName = "Vocabulary", foreignKeys = [(ForeignKey(entity = Translation::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("translationId")))])
data class Vocabulary (

    @PrimaryKey(autoGenerate = true) val id: Long,
    var tibetanWord: String,
    var audioFileName: String,
    var pictureFileName: String,
    var translationId: Long
)