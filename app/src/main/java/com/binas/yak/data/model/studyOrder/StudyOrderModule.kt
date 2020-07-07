package com.binas.yak.data.model.studyOrder

import dagger.Module
import dagger.Provides

@Module
class StudyOrderModule {

    @Provides
    fun provideStudyOrderRepository(repository: StudyOrderRepositoryImpl): StudyOrderRepository = repository
}