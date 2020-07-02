package com.binas.yak.data.model.userSignRevisionFlashcard

import androidx.room.Entity
import androidx.room.ForeignKey
import com.binas.yak.data.model.User
import com.binas.yak.data.model.sign.SignRevisionFlashcard

@Entity(tableName ="User_SignRevisionFlashcard", primaryKeys = ["userId", "signRevisionFlashcardId"],
    foreignKeys = [(ForeignKey(entity = User::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("userId"))), ForeignKey(entity = SignRevisionFlashcard::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("signRevisionFlashcardId"))])
data class UserSignRevisionFlashcard (
    val userId: Long,
    val signRevisionFlashcardId: Long
)