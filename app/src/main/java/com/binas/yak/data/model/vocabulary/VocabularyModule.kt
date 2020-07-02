package com.binas.yak.data.model.vocabulary

import dagger.Module
import dagger.Provides

@Module
class VocabularyModule {

    @Provides
    fun provideVocabularyRepository(repository: VocabularyRepositoryImpl): VocabularyRepository = repository
}