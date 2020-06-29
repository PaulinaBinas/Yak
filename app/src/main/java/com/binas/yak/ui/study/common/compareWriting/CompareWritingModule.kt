package com.binas.yak.ui.study.common.compareWriting

import com.binas.yak.ui.study.common.compareWriting.interactor.CompareWritingInteractor
import com.binas.yak.ui.study.common.compareWriting.interactor.CompareWritingInteractorImpl
import com.binas.yak.ui.study.common.compareWriting.presenter.CompareWritingPresenter
import com.binas.yak.ui.study.common.compareWriting.presenter.CompareWritingPresenterImpl
import com.binas.yak.ui.study.common.compareWriting.view.CompareWritingView
import dagger.Module
import dagger.Provides

@Module
class CompareWritingModule {

    @Provides
    internal fun provideCompareWritingInteractor(interactor: CompareWritingInteractorImpl): CompareWritingInteractor = interactor

    @Provides
    internal fun provideCompareWritingPresenter(presenter: CompareWritingPresenterImpl<CompareWritingView, CompareWritingInteractor>): CompareWritingPresenter<CompareWritingView, CompareWritingInteractor> = presenter
}