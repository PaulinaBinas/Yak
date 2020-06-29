package com.binas.yak.ui.study.common.reviseWriting

import com.binas.yak.ui.study.common.reviseWriting.interactor.ReviseWritingInteractor
import com.binas.yak.ui.study.common.reviseWriting.interactor.ReviseWritingInteractorImpl
import com.binas.yak.ui.study.common.reviseWriting.presenter.ReviseWritingPresenter
import com.binas.yak.ui.study.common.reviseWriting.presenter.ReviseWritingPresenterImpl
import com.binas.yak.ui.study.common.reviseWriting.view.ReviseWritingView
import dagger.Module
import dagger.Provides

@Module
class ReviseWritingModule {

    @Provides
    internal fun provideReviseWritingInteractor(interactor: ReviseWritingInteractorImpl): ReviseWritingInteractor = interactor

    @Provides
    internal fun provideReviseWritingPresenter(presenter: ReviseWritingPresenterImpl<ReviseWritingView, ReviseWritingInteractor>)
            : ReviseWritingPresenter<ReviseWritingView, ReviseWritingInteractor> = presenter
}