package com.binas.yak.ui.study.common.correct

import com.binas.yak.ui.study.common.correct.interactor.CorrectInteractor
import com.binas.yak.ui.study.common.correct.interactor.CorrectInteractorImpl
import com.binas.yak.ui.study.common.correct.presenter.CorrectPresenter
import com.binas.yak.ui.study.common.correct.presenter.CorrectPresenterImpl
import com.binas.yak.ui.study.common.correct.view.CorrectView
import dagger.Module
import dagger.Provides

@Module
class CorrectModule {

    @Provides
    internal fun provideCorrectInteractor(interactor: CorrectInteractorImpl): CorrectInteractor = interactor

    @Provides
    internal fun provideCorrectPresenter(presenter: CorrectPresenterImpl<CorrectView, CorrectInteractor>)
            : CorrectPresenter<CorrectView, CorrectInteractor> = presenter
}