package com.binas.yak.ui.studiedElements.presenter

import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.studiedElements.interactor.StudiedElementsInteractor
import com.binas.yak.ui.studiedElements.view.StudiedElementsView
import javax.inject.Inject

class StudiedElementsPresenterImpl<V: StudiedElementsView, I: StudiedElementsInteractor>
@Inject internal constructor(interactor: I): BasePresenter<V, I>(interactor = interactor), StudiedElementsPresenter<V, I> {
}