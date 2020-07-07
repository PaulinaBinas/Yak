package com.binas.yak.data.model.grammar

import android.view.ViewDebug
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.binas.yak.data.model.Flashcard
import com.binas.yak.data.model.vocabulary.Vocabulary

@Entity(tableName = "GrammarStudyFlashcard", foreignKeys = [(ForeignKey(entity = Grammar::class,
parentColumns = arrayOf("id"),
childColumns = arrayOf("grammarId"))), (ForeignKey(entity = Vocabulary::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("chosenVocabularyId")))])
data class GrammarStudyFlashcard (
    @PrimaryKey(autoGenerate = true) var id: Long,
    var grammarId: Long
): Flashcard {
    override fun compareTo(other: Flashcard): Int {
        return if(other is GrammarStudyFlashcard && other.id > this.id) 0 else 1
    }

    var ifStudied: Long = 0
    var userDescription: String? = null
    var chosenVocabularyId: Long? = null
}
