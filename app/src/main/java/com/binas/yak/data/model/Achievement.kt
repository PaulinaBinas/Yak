package com.binas.yak.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.binas.yak.data.model.translation.Translation

@Entity(tableName = "Achievement", foreignKeys = [(ForeignKey(entity = Translation::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("nameTranslationId"))), (ForeignKey(entity = Translation::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("descriptionTranslationId")))])
data class Achievement (
    @PrimaryKey val id: Long,
    var nameTranslationId: Long,
    var descriptionTranslationId: Long
)