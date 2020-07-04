package com.binas.yak.data.model.achievements

import dagger.Module
import dagger.Provides

@Module
class AchievementsModule {

    @Provides
    fun provideAchievementsRepository(repository: AchievementsRepositoryImpl): AchievementsRepository = repository
}