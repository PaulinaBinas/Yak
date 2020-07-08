package com.binas.yak.di.component

import android.app.Application
import com.binas.yak.YakApplication
import com.binas.yak.di.builder.ActivityBuilder
import com.binas.yak.di.module.AppModule
import com.binas.yak.util.UtilModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(UtilModule::class), (AndroidInjectionModule::class), (AppModule::class), (ActivityBuilder::class)])
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: YakApplication)
}