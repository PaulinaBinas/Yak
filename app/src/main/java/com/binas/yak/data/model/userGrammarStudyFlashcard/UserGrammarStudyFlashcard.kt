package com.binas.yak.data.model.userGrammarStudyFlashcard

import androidx.room.Entity
import androidx.room.ForeignKey
import com.binas.yak.data.model.grammar.GrammarStudyFlashcard
import com.binas.yak.data.model.user.User

@Entity(tableName = "User_GrammarStudyFlashcard", primaryKeys = ["userId"],
    foreignKeys = [(ForeignKey(entity = User::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("userId"))), ForeignKey(entity = GrammarStudyFlashcard::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("grammarStudyFlashcardId"))])
data class UserGrammarStudyFlashcard (
    val userId: Long,
    val grammarStudyFlashcardId: Long
)