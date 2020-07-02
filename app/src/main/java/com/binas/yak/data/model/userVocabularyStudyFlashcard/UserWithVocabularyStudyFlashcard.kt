package com.binas.yak.data.model.userVocabularyStudyFlashcard

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.binas.yak.data.model.User
import com.binas.yak.data.model.vocabulary.VocabularyStudyFlashcard

data class UserWithVocabularyStudyFlashcard (
    @Embedded
    val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = UserVocabularyStudyFlashcard::class,
            parentColumn = "userId",
            entityColumn = "vocabularyStudyFlashcardId"
        )
    )
    val flashcards: List<VocabularyStudyFlashcard>
)