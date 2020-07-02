package com.binas.yak.data.model.userVocabularyRevisionFlashcard

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.binas.yak.data.model.User
import com.binas.yak.data.model.vocabulary.VocabularyRevisionFlashcard

data class UserWithVocabularyRevisionFlashcard (
    @Embedded
    val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = UserVocabularyRevisionFlashcard::class,
            parentColumn = "userId",
            entityColumn = "vocabularyRevisionFlashcardId"
        )
    )
    val flashcards: List<VocabularyRevisionFlashcard>
)