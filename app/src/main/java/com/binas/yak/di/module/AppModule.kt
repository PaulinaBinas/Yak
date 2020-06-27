package com.binas.yak.di.module

import android.app.Application
import android.content.Context
import com.binas.yak.data.preferences.PreferenceHelper
import com.binas.yak.data.preferences.PreferenceHelperImpl
import com.binas.yak.util.ApplicationConstants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    internal fun provideprefFileName(): String = ApplicationConstants.PREF_NAME

    @Provides
    @Singleton
    internal fun providePrefHelper(appPreferenceHelper: PreferenceHelperImpl): PreferenceHelper = appPreferenceHelper

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application

}