package com.binas.yak.data.model.userSignStudyFlashcard

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.binas.yak.data.model.SignStudyFlashcard
import com.binas.yak.data.model.User

data class UserWithSighStudyFlashcard (
    @Embedded
    val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = UserSignStudyFlashcard::class,
            parentColumn = "userId",
            entityColumn = "flashcardId"
        )
    )
    val flashcards: List<SignStudyFlashcard>
)