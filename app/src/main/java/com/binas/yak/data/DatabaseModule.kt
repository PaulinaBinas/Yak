package com.binas.yak.data

import android.app.Application
import androidx.room.Room
import com.binas.yak.data.model.achievements.AchievementsDao
import com.binas.yak.data.model.grammar.GrammarDao
import com.binas.yak.data.model.sign.SignDao
import com.binas.yak.data.model.studyDay.StudyDayDao
import com.binas.yak.data.model.studyOrder.StudyOrderDao
import com.binas.yak.data.model.translation.TranslationDao
import com.binas.yak.data.model.vocabulary.VocabularyDao
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @Provides
    fun provideSignDao(db: ApplicationDatabase): SignDao = db.signDao()

    @Provides
    fun provideTranslationDao(db: ApplicationDatabase): TranslationDao = db.translationDao()

    @Provides
    fun provideVocabularyDao(db: ApplicationDatabase): VocabularyDao = db.vocabularyDao()

    @Provides
    fun provideGrammarDao(db: ApplicationDatabase): GrammarDao = db.grammarDao()

    @Provides
    fun provideAchievementsDao(db: ApplicationDatabase): AchievementsDao = db.achievementsDao()

    @Provides
    fun provideStudyOrderDao(db: ApplicationDatabase): StudyOrderDao = db.studyOrderDao()

    @Provides
    fun provideStudyDayDao(db: ApplicationDatabase): StudyDayDao = db.studyDayDao()

    @Provides
    fun provideDatabase(context: Application): ApplicationDatabase = Room
        .databaseBuilder(context, ApplicationDatabase::class.java, "app.db")
        .createFromAsset("database/tibetan.db")
        .build()


}