package com.binas.yak.data.model.grammar

import dagger.Module
import dagger.Provides

@Module
class GrammarModule {

    @Provides
    fun provideGrammarrepository(repository: GrammarRepositoryImpl): GrammarRepository = repository
}