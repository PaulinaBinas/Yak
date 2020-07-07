package com.binas.yak.ui.study

import com.binas.yak.ui.study.interactor.StudyInteractor
import com.binas.yak.ui.study.interactor.StudyInteractorImpl
import com.binas.yak.ui.study.presenter.StudyPresenter
import com.binas.yak.ui.study.presenter.StudyPresenterImpl
import com.binas.yak.ui.study.view.StudyView
import dagger.Module
import dagger.Provides

@Module
class StudyModule {

    @Provides
    internal fun provideStudyInteractor(interactor: StudyInteractorImpl): StudyInteractor = interactor

    @Provides
    internal fun provideStudyPresenter(presenter: StudyPresenterImpl<StudyView, StudyInteractor>): StudyPresenter<StudyView, StudyInteractor> = presenter
}