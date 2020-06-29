package com.binas.yak.ui.study.common.incorrect

import com.binas.yak.ui.study.common.incorrect.interactor.IncorrectInteractor
import com.binas.yak.ui.study.common.incorrect.interactor.IncorrectInteractorImpl
import com.binas.yak.ui.study.common.incorrect.presenter.IncorrectPresenter
import com.binas.yak.ui.study.common.incorrect.presenter.IncorrectPresenterImpl
import com.binas.yak.ui.study.common.incorrect.view.IncorrectView
import dagger.Module
import dagger.Provides

@Module
class IncorrectModule {

    @Provides
    internal fun provideIncorrectInteractor(interactor: IncorrectInteractorImpl): IncorrectInteractor = interactor

    @Provides
    internal fun provideIncorrectPresenter(presenter: IncorrectPresenterImpl<IncorrectView, IncorrectInteractor>)
            : IncorrectPresenter<IncorrectView, IncorrectInteractor> = presenter
}