package com.binas.yak.ui.others.drawing

import com.binas.yak.ui.others.drawing.interactor.DrawingInteractor
import com.binas.yak.ui.others.drawing.interactor.DrawingInteractorImpl
import com.binas.yak.ui.others.drawing.presenter.DrawingPresenter
import com.binas.yak.ui.others.drawing.presenter.DrawingPresenterImpl
import com.binas.yak.ui.others.drawing.view.DrawingAdapter
import com.binas.yak.ui.others.drawing.view.DrawingView
import dagger.Module
import dagger.Provides

@Module
class DrawingModule {

    @Provides
    internal fun provideDrawingInteractor(interactor: DrawingInteractorImpl): DrawingInteractor = interactor

    @Provides
    internal fun provideDrawingPresenter(presenter: DrawingPresenterImpl<DrawingView, DrawingInteractor>)
            : DrawingPresenter<DrawingView, DrawingInteractor> = presenter

    @Provides
    internal fun provideDrawingAdapter(): DrawingAdapter = DrawingAdapter()
}