package com.binas.yak.data.model.userSignStudyFlashcard

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.binas.yak.data.model.sign.SignStudyFlashcard
import com.binas.yak.data.model.user.User

data class UserWithSighStudyFlashcard (
    @Embedded
    val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = UserSignStudyFlashcard::class,
            parentColumn = "userId",
            entityColumn = "signStudyFlashcardId"
        )
    )
    val flashcards: List<SignStudyFlashcard>
)