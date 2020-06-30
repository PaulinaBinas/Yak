package com.binas.yak.data.model.grammarStudyFlashcardVocabulary

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.binas.yak.data.model.User
import com.binas.yak.data.model.Vocabulary
import com.binas.yak.data.model.userStudyDay.UserStudyDay

data class GrammarStudyFlashcardWithVocabulary (
    @Embedded
    val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = GrammarStudyFlashcardVocabulary::class,
            parentColumn = "flashcardId",
            entityColumn = "vocabularyId"
        )
    )
    val vocabularyList: List<Vocabulary>
)