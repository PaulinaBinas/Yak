package com.binas.yak.data.model.sign

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.binas.yak.data.model.sign.Sign

@Entity(tableName = "SignStudyFlashcard", foreignKeys = [(ForeignKey(entity = Sign::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("signId")))])
data class SignStudyFlashcard (
    @PrimaryKey(autoGenerate = true) var id: Long,
    var signId: Long,
    var ifStudied: Long = 0,
    var userDescription: String? = null
)