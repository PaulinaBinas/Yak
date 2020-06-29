package com.binas.yak.ui.others.image

import com.binas.yak.ui.others.image.view.ImageFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ImageProvider {

    @ContributesAndroidInjector(modules = [ImageModule::class])
    internal abstract fun provideImageFragmentFactory(): ImageFragment
}