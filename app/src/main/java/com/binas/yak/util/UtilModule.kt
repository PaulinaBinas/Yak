package com.binas.yak.util

import com.binas.yak.util.impl.DailyFlashcardQueueImpl
import com.binas.yak.util.impl.SpacedRepetitionSchedulerImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UtilModule {

    @Provides
    @Singleton
    fun provideDailyFlashcardQueue(): DailyFlashcardQueue = DailyFlashcardQueueImpl()

    @Provides
    fun provideSpacedRepetitionScheduler(scheduler: SpacedRepetitionSchedulerImpl): SpacedRepetitionScheduler = scheduler
}