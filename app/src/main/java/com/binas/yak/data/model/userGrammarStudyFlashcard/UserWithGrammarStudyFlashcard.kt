package com.binas.yak.data.model.userGrammarStudyFlashcard

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.binas.yak.data.model.GrammarStudyFlashcard
import com.binas.yak.data.model.User

class UserWithGrammarStudyFlashcard (
    @Embedded
    val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = UserGrammarStudyFlashcard::class,
            parentColumn = "userId",
            entityColumn = "flashcardId"
        )
    )
    val flashcards: List<GrammarStudyFlashcard>
)
