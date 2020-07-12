package com.binas.yak.data.model.userVocabularyStudyFlashcard

import androidx.room.Entity
import androidx.room.ForeignKey
import com.binas.yak.data.model.user.User
import com.binas.yak.data.model.vocabulary.VocabularyStudyFlashcard

@Entity(tableName = "User_VocabularyStudyFlashcard", primaryKeys = ["userId"],
    foreignKeys = [(ForeignKey(entity = User::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("userId"))), ForeignKey(entity = VocabularyStudyFlashcard::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("vocabularyStudyFlashcardId"))])
data class UserVocabularyStudyFlashcard (
    val userId: Long,
    val vocabularyStudyFlashcardId: Long
)