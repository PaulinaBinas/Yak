package com.binas.yak.di.module

import com.binas.yak.util.ApplicationConstants
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    internal fun provideprefFileName(): String = ApplicationConstants.PREF_NAME
}