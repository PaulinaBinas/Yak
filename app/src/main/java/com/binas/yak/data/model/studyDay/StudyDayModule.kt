package com.binas.yak.data.model.studyDay

import dagger.Module
import dagger.Provides

@Module
class StudyDayModule {

    @Provides
    fun provideStudyDayRepository(repository: StudyDayRepositoryImpl): StudyDayRepository = repository
}