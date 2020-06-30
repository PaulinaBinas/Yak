package com.binas.yak.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.binas.yak.data.model.*
import com.binas.yak.util.impl.Converters

@Database(entities = [(Sign::class), (Grammar::class), (Vocabulary::class),
    (SignRevisionFlashcard::class), (SignStudyFlashcard::class), (GrammarRevisionFlashcard::class),
    (GrammarStudyFlashcard::class), (VocabularyStudyFlashcard::class), (User::class),
    (StudyDay::class), (Translation::class)], version = 1)
@TypeConverters(Converters::class)
abstract class ApplicationDatabase: RoomDatabase() {
}