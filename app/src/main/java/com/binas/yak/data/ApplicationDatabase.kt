package com.binas.yak.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.binas.yak.data.model.*
import com.binas.yak.data.model.grammarStudyFlashcardVocabulary.GrammarStudyFlashcardVocabulary
import com.binas.yak.data.model.sign.Sign
import com.binas.yak.data.model.sign.SignDao
import com.binas.yak.data.model.sign.SignRevisionFlashcard
import com.binas.yak.data.model.sign.SignStudyFlashcard
import com.binas.yak.data.model.userGrammarRevisionFlashcard.UserGrammarRevisionFlashcard
import com.binas.yak.data.model.userGrammarStudyFlashcard.UserGrammarStudyFlashcard
import com.binas.yak.data.model.userSignRevisionFlashcard.UserSignRevisionFlashcard
import com.binas.yak.data.model.userSignStudyFlashcard.UserSignStudyFlashcard
import com.binas.yak.data.model.userSignStudyFlashcard.UserSignStudyFlashcardDao
import com.binas.yak.data.model.userStudyDay.UserStudyDay
import com.binas.yak.data.model.userVocabularyRevisionFlashcard.UserVocabularyRevisionFlashcard
import com.binas.yak.data.model.userVocabularyStudyFlashcard.UserVocabularyStudyFlashcard
import com.binas.yak.util.impl.Converters
import javax.inject.Inject


@Database(entities = [(Sign::class), (Grammar::class), (Vocabulary::class),
    (SignRevisionFlashcard::class), (SignStudyFlashcard::class), (GrammarRevisionFlashcard::class),
    (GrammarStudyFlashcard::class), (VocabularyRevisionFlashcard::class),
    (VocabularyStudyFlashcard::class), (User::class),
    (UserGrammarRevisionFlashcard::class), (GrammarStudyFlashcardVocabulary::class),
    (UserGrammarStudyFlashcard::class), (UserSignRevisionFlashcard::class),
    (UserSignStudyFlashcard::class), (UserStudyDay::class), (UserVocabularyRevisionFlashcard::class),
    (UserVocabularyStudyFlashcard::class), (StudyDay::class), (Translation::class)], version = 1)
@TypeConverters(Converters::class)
abstract class ApplicationDatabase: RoomDatabase() {
    abstract fun userSignStudyFlashcardDao(): UserSignStudyFlashcardDao
    abstract fun signDao(): SignDao
}