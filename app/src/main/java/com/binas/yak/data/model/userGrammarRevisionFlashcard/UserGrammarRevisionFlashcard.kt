package com.binas.yak.data.model.userGrammarRevisionFlashcard

import androidx.room.Entity
import androidx.room.ForeignKey
import com.binas.yak.data.model.GrammarRevisionFlashcard
import com.binas.yak.data.model.User

@Entity(tableName = "User_GrammarRevisionFlashcard", primaryKeys = ["userId", "grammarRevisionFlashcardId"],
    foreignKeys = [(ForeignKey(entity = User::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("userId"))), ForeignKey(entity = GrammarRevisionFlashcard::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("grammarRevisionFlashcardId"))])
data class UserGrammarRevisionFlashcard (
    val userId: Long,
    val grammarRevisionFlashcardId: Long
)
