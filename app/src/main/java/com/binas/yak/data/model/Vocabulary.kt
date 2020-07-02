package com.binas.yak.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.binas.yak.data.model.translation.Translation

@Entity(tableName = "Vocabulary", foreignKeys = [(ForeignKey(entity = Translation::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("translationId")))])
data class Vocabulary (

    @PrimaryKey(autoGenerate = true) val id: Long,
    var tibetanWord: String?,
    var audioFileName: String?,
    var pictureFileName: String?,
    var translationId: Long?
)