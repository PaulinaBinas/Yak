package com.binas.yak.ui.studiedElements.presenter

import com.binas.yak.ui.base.presenter.Presenter
import com.binas.yak.ui.studiedElements.interactor.StudiedElementsInteractor
import com.binas.yak.ui.studiedElements.view.StudiedElementsView

interface StudiedElementsPresenter<V: StudiedElementsView, I: StudiedElementsInteractor>: Presenter<V, I> {
}