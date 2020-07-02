package com.binas.yak.data.model.userSignStudyFlashcard

import androidx.room.Entity
import androidx.room.ForeignKey
import com.binas.yak.data.model.User
import com.binas.yak.data.model.sign.SignRevisionFlashcard
import com.binas.yak.data.model.sign.SignStudyFlashcard

@Entity(tableName = "User_SignStudyFlashcard", primaryKeys = ["userId"],
    foreignKeys = [(ForeignKey(entity = User::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("userId"))), ForeignKey(entity = SignStudyFlashcard::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("signStudyFlashcardId"))])
data class UserSignStudyFlashcard (
    val userId: Long,
    val signStudyFlashcardId: Long
)