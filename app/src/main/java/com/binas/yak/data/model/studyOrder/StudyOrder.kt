package com.binas.yak.data.model.studyOrder

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.binas.yak.data.model.grammar.GrammarStudyFlashcard
import com.binas.yak.data.model.sign.SignStudyFlashcard
import com.binas.yak.data.model.translation.Translation
import com.binas.yak.data.model.vocabulary.VocabularyStudyFlashcard

@Entity(tableName = "StudyOrder", foreignKeys = [(ForeignKey(entity = SignStudyFlashcard::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("signStudyId"))), (ForeignKey(entity = GrammarStudyFlashcard::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("grammarStudyId"))), (ForeignKey(entity = VocabularyStudyFlashcard::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("vocabularyStudyId")))])
data class StudyOrder (
    @PrimaryKey(autoGenerate = true) val id: Long,
    var signStudyId: Long?,
    var grammarStudyId: Long?,
    var vocabularyStudyId: Long?
)