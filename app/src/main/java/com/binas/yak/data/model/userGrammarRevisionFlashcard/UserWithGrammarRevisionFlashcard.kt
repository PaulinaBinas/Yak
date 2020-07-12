package com.binas.yak.data.model.userGrammarRevisionFlashcard

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.binas.yak.data.model.grammar.GrammarRevisionFlashcard
import com.binas.yak.data.model.user.User

data class UserWithGrammarRevisionFlashcard (
    @Embedded
    val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = UserGrammarRevisionFlashcard::class,
            parentColumn = "userId",
            entityColumn = "grammarRevisionFlashcardId"
        )
    )
    val flashcards: List<GrammarRevisionFlashcard>
)