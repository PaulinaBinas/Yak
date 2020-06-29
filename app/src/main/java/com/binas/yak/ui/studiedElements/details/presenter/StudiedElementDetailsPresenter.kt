package com.binas.yak.ui.studiedElements.details.presenter

import com.binas.yak.ui.base.presenter.Presenter
import com.binas.yak.ui.studiedElements.details.interactor.StudiedElementDetailsInteractor
import com.binas.yak.ui.studiedElements.details.view.StudiedElementDetailsView

interface StudiedElementDetailsPresenter<V: StudiedElementDetailsView, I: StudiedElementDetailsInteractor>: Presenter<V, I> {
}