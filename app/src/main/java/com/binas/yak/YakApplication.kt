package com.binas.yak

import android.app.Application
import com.binas.yak.data.preferences.PreferenceHelper
import com.binas.yak.di.component.AppComponent
import com.binas.yak.di.component.DaggerAppComponent
import com.yariksoffice.lingver.Lingver
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject
import dagger.android.HasAndroidInjector

class YakApplication: Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector : DispatchingAndroidInjector<Any>
    @Inject
    lateinit var preferenceHelper: PreferenceHelper

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
        Lingver.init(this, LANGUAGE_ENGLISH)
        if(!preferenceHelper.getIsLanguageSet()) {
            Lingver.getInstance().setFollowSystemLocale(this)
        }
    }

    companion object {
        const val LANGUAGE_ENGLISH = "en"
        const val LANGUAGE_ENGLISH_COUNTRY = "US"
        const val LANGUAGE_POLISH = "pl"
        const val LANGUAGE_POLISH_COUNTRY = "PL"
    }
}