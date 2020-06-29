package com.binas.yak.ui.studiedElements.details

import com.binas.yak.ui.studiedElements.details.interactor.StudiedElementDetailsInteractor
import com.binas.yak.ui.studiedElements.details.interactor.StudiedElementDetailsInteractorImpl
import com.binas.yak.ui.studiedElements.details.presenter.StudiedElementDetailsPresenter
import com.binas.yak.ui.studiedElements.details.presenter.StudiedElementDetailsPresenterImpl
import com.binas.yak.ui.studiedElements.details.view.StudiedElementDetailsView
import dagger.Module
import dagger.Provides

@Module
class StudiedElementDetailsModule {

    @Provides
    internal fun provideStudiedElementDetailsInteractor(interactor: StudiedElementDetailsInteractorImpl): StudiedElementDetailsInteractor = interactor

    @Provides
    internal fun provideStudiedElementDetailsPresenter(presenter: StudiedElementDetailsPresenterImpl<StudiedElementDetailsView, StudiedElementDetailsInteractor>)
            : StudiedElementDetailsPresenter<StudiedElementDetailsView, StudiedElementDetailsInteractor> = presenter
}