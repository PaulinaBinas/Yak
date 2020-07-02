package com.binas.yak.data.model.sign

import dagger.Module
import dagger.Provides

@Module
class SignModule {

    @Provides
    fun provideSignRepository(signRepository: SignRepositoryImpl): SignRepository = signRepository
}