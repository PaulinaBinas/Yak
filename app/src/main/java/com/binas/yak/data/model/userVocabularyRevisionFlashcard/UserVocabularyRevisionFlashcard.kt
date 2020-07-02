package com.binas.yak.data.model.userVocabularyRevisionFlashcard

import androidx.room.Entity
import androidx.room.ForeignKey
import com.binas.yak.data.model.User
import com.binas.yak.data.model.VocabularyRevisionFlashcard
import com.binas.yak.data.model.sign.SignStudyFlashcard

@Entity(tableName = "User_VocabularyRevisionFlashcard", primaryKeys = ["userId", "vocabularyRevisionFlashcardId"],
    foreignKeys = [(ForeignKey(entity = User::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("userId"))), ForeignKey(entity = VocabularyRevisionFlashcard::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("vocabularyRevisionFlashcardId"))])
data class UserVocabularyRevisionFlashcard (
    val userId: Long,
    val vocabularyRevisionFlashcardId: Long
)