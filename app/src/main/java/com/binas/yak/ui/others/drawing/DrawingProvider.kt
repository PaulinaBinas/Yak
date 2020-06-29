package com.binas.yak.ui.others.drawing

import com.binas.yak.ui.others.drawing.view.DrawingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class DrawingProvider {

    @ContributesAndroidInjector(modules = [DrawingModule::class])
    internal abstract fun provideDrawingFragmentFactory(): DrawingFragment
}