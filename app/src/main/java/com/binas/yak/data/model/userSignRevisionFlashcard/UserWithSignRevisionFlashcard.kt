package com.binas.yak.data.model.userSignRevisionFlashcard

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.binas.yak.data.model.SignRevisionFlashcard
import com.binas.yak.data.model.User

data class UserWithSignRevisionFlashcard (
    @Embedded
    val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = UserSignRevisionFlashcard::class,
            parentColumn = "userId",
            entityColumn = "flashcardId"
        )
    )
    val flashcards: List<SignRevisionFlashcard>
)