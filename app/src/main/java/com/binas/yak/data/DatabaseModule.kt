package com.binas.yak.data

import android.app.Application
import androidx.room.Room
import com.binas.yak.data.model.sign.SignDao
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
    fun provideDatabase(context: Application): ApplicationDatabase = Room
        .databaseBuilder(context, ApplicationDatabase::class.java, "app.db")
        .createFromAsset("database/tibetan.db")
        .build()


}