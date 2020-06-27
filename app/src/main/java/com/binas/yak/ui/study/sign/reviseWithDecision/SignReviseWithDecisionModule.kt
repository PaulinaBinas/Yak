package com.binas.yak.ui.study.sign.reviseWithDecision

import com.binas.yak.ui.study.sign.reviseWithDecision.interactor.SignReviseWithDecisionInteractor
import com.binas.yak.ui.study.sign.reviseWithDecision.interactor.SignReviseWithDecisionInteractorImpl
import com.binas.yak.ui.study.sign.reviseWithDecision.presenter.SignReviseWithDecisionPresenter
import com.binas.yak.ui.study.sign.reviseWithDecision.presenter.SignReviseWithDecisionPresenterImpl
import com.binas.yak.ui.study.sign.reviseWithDecision.view.SignReviseWithDecisionView
import dagger.Module
import dagger.Provides

@Module
class SignReviseWithDecisionModule {

    @Provides
    internal fun provideSignReviseWithDecisionInteractor(interactor: SignReviseWithDecisionInteractorImpl): SignReviseWithDecisionInteractor = interactor

    @Provides
    internal fun provideSignReviseWithDecisionPresenter(presenter: SignReviseWithDecisionPresenterImpl<SignReviseWithDecisionView, SignReviseWithDecisionInteractor>)
            : SignReviseWithDecisionPresenter<SignReviseWithDecisionView, SignReviseWithDecisionInteractor> = presenter
}