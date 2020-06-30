package com.binas.yak.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "signStudyFlashcard", foreignKeys = [(ForeignKey(entity = Sign::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("signId"),
    onDelete = ForeignKey.CASCADE))])
data class SignStudyFlashcard (
    @PrimaryKey(autoGenerate = true) var id: Long,
    var signId: Long
)