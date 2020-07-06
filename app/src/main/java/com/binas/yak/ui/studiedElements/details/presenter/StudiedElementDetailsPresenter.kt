package com.binas.yak.ui.studiedElements.details.presenter

import com.binas.yak.data.model.translation.Translation
import com.binas.yak.ui.base.presenter.Presenter
import com.binas.yak.ui.studiedElements.details.interactor.StudiedElementDetailsInteractor
import com.binas.yak.ui.studiedElements.details.view.StudiedElementDetailsView
import java.time.LocalDate

interface StudiedElementDetailsPresenter<V: StudiedElementDetailsView, I: StudiedElementDetailsInteractor>: Presenter<V, I> {

    fun getTranslation(id: Long): Translation?

    fun getDaysTillRevision(type: String, id: Long): Int
}