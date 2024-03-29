package com.binas.yak.data.model.userGrammarStudyFlashcard

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.binas.yak.data.model.grammar.GrammarStudyFlashcard
import com.binas.yak.data.model.user.User

class UserWithGrammarStudyFlashcard (
    @Embedded
    val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = UserGrammarStudyFlashcard::class,
            parentColumn = "userId",
            entityColumn = "grammarStudyFlashcardId"
        )
    )
    val flashcards: List<GrammarStudyFlashcard>
)
