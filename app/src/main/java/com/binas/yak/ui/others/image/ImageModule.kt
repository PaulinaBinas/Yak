package com.binas.yak.ui.others.image

import com.binas.yak.ui.others.image.interactor.ImageInteractor
import com.binas.yak.ui.others.image.interactor.ImageInteractorImpl
import com.binas.yak.ui.others.image.presenter.ImagePresenter
import com.binas.yak.ui.others.image.presenter.ImagePresenterImpl
import com.binas.yak.ui.others.image.view.ImageAdapter
import com.binas.yak.ui.others.image.view.ImageView
import dagger.Module
import dagger.Provides

@Module
class ImageModule {

    @Provides
    internal fun provideImageInteractor(interactor: ImageInteractorImpl): ImageInteractor = interactor

    @Provides
    internal fun provideImagePresenter(presenter: ImagePresenterImpl<ImageView, ImageInteractor>)
            : ImagePresenter<ImageView, ImageInteractor> = presenter

    @Provides
    internal fun provideImageAdapter(): ImageAdapter = ImageAdapter()
}