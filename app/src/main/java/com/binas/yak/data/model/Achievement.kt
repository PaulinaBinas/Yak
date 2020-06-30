package com.binas.yak.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "grammar", foreignKeys = [(ForeignKey(entity = Translation::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("nameTranslationId"),
    onDelete = ForeignKey.CASCADE)), (ForeignKey(entity = Translation::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("descriptionTranslationId")))])
data class Achievement (
    @PrimaryKey val id: Long,
    var nameTranslationId: Long,
    var descriptionTranslationId: Long
)