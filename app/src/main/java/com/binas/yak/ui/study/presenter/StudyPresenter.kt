package com.binas.yak.ui.study.presenter

import com.binas.yak.ui.base.presenter.Presenter
import com.binas.yak.ui.study.interactor.StudyInteractor
import com.binas.yak.ui.study.view.StudyView

interface StudyPresenter<V: StudyView, I: StudyInteractor>: Presenter<V, I> {

    fun start()
}