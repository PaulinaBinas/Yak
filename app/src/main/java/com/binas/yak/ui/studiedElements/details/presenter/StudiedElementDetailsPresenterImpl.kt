package com.binas.yak.ui.studiedElements.details.presenter

import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.studiedElements.details.interactor.StudiedElementDetailsInteractor
import com.binas.yak.ui.studiedElements.details.view.StudiedElementDetailsView
import javax.inject.Inject

class StudiedElementDetailsPresenterImpl<V: StudiedElementDetailsView, I: StudiedElementDetailsInteractor>
@Inject internal constructor(interactor: I): BasePresenter<V, I>(interactor = interactor), StudiedElementDetailsPresenter<V, I> {
}