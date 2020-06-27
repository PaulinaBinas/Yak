package com.binas.yak.ui.study.sign.reviseWriting

import com.binas.yak.ui.study.sign.reviseWriting.interactor.SignReviseWritingInteractor
import com.binas.yak.ui.study.sign.reviseWriting.interactor.SignReviseWritingInteractorImpl
import com.binas.yak.ui.study.sign.reviseWriting.presenter.SignReviseWritingPresenter
import com.binas.yak.ui.study.sign.reviseWriting.presenter.SignReviseWritingPresenterImpl
import com.binas.yak.ui.study.sign.reviseWriting.view.SignReviseWritingView
import dagger.Module
import dagger.Provides

@Module
class SignReviseWritingModule {

    @Provides
    internal fun provideSignReviseWritingInteractor(interactor: SignReviseWritingInteractorImpl)
            : SignReviseWritingInteractor = interactor

    @Provides
    internal fun provideSignReviseWritingPresenter(presenter: SignReviseWritingPresenterImpl<SignReviseWritingView, SignReviseWritingInteractor>):
            SignReviseWritingPresenter<SignReviseWritingView, SignReviseWritingInteractor> = presenter
}