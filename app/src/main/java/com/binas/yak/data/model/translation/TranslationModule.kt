package com.binas.yak.data.model.translation

import dagger.Module
import dagger.Provides

@Module
class TranslationModule {

    @Provides
    fun provideTranslationRepository(repository: TranslationRepositoryImpl): TranslationRepository = repository
}