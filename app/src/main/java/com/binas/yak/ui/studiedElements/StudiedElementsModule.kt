package com.binas.yak.ui.studiedElements

import com.binas.yak.ui.studiedElements.interactor.StudiedElementsInteractor
import com.binas.yak.ui.studiedElements.interactor.StudiedElementsInteractorImpl
import com.binas.yak.ui.studiedElements.presenter.StudiedElementsPresenter
import com.binas.yak.ui.studiedElements.presenter.StudiedElementsPresenterImpl
import com.binas.yak.ui.studiedElements.view.StudiedElementsView
import dagger.Module
import dagger.Provides

@Module
class StudiedElementsModule {

    @Provides
    internal fun provideStudiedElementsInteractor(interactor: StudiedElementsInteractorImpl): StudiedElementsInteractor = interactor

    @Provides
    internal fun provideStudiedElementsPresenetr(presenter: StudiedElementsPresenterImpl<StudiedElementsView, StudiedElementsInteractor>)
            : StudiedElementsPresenter<StudiedElementsView, StudiedElementsInteractor> = presenter
}