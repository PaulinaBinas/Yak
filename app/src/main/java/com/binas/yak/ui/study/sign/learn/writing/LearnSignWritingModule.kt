package com.binas.yak.ui.study.sign.learn.writing

import com.binas.yak.ui.study.sign.learn.writing.interactor.LearnSignWritingInteractor
import com.binas.yak.ui.study.sign.learn.writing.interactor.LearnSignWritingInteractorImpl
import com.binas.yak.ui.study.sign.learn.writing.presenter.LearnSignWritingPresenter
import com.binas.yak.ui.study.sign.learn.writing.presenter.LearnSignWritingPresenterImpl
import com.binas.yak.ui.study.sign.learn.writing.view.LearnSignWritingView
import dagger.Module
import dagger.Provides

@Module
class LearnSignWritingModule {

    @Provides
    internal fun provideLearnSignWritingInteractor(interactor: LearnSignWritingInteractorImpl): LearnSignWritingInteractor = interactor

    @Provides
    internal fun provideLearnSignWritingPresenter(presenter: LearnSignWritingPresenterImpl<LearnSignWritingView, LearnSignWritingInteractor>)
            : LearnSignWritingPresenter<LearnSignWritingView, LearnSignWritingInteractor> = presenter
}