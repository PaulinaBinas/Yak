package com.binas.yak.ui.study.sign.learn.studyCard

import com.binas.yak.ui.study.sign.learn.studyCard.interactor.SignStudyCardInteractor
import com.binas.yak.ui.study.sign.learn.studyCard.interactor.SignStudyCardInteractorImpl
import com.binas.yak.ui.study.sign.learn.studyCard.presenter.SignStudyCardPresenter
import com.binas.yak.ui.study.sign.learn.studyCard.presenter.SignStudyCardPresenterImpl
import com.binas.yak.ui.study.sign.learn.studyCard.view.SignStudyCardView
import dagger.Module
import dagger.Provides

@Module
class SignStudyCardModule {

    @Provides
    internal fun provideSignStudyCardInteractor(interactor: SignStudyCardInteractorImpl): SignStudyCardInteractor = interactor

    @Provides
    internal fun provideSignStudyCardPresenter(presenter: SignStudyCardPresenterImpl<SignStudyCardView, SignStudyCardInteractor>)
            : SignStudyCardPresenter<SignStudyCardView, SignStudyCardInteractor> = presenter
}