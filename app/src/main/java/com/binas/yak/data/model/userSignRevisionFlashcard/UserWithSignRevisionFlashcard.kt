package com.binas.yak.data.model.userSignRevisionFlashcard

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.binas.yak.data.model.sign.SignRevisionFlashcard
import com.binas.yak.data.model.user.User

data class UserWithSignRevisionFlashcard (
    @Embedded
    val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = UserSignRevisionFlashcard::class,
            parentColumn = "userId",
            entityColumn = "signRevisionFlashcardId"
        )
    )
    val flashcards: List<SignRevisionFlashcard>
)