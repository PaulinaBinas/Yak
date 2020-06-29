package com.binas.yak.ui.others.drawing.presenter

import com.binas.yak.ui.base.presenter.Presenter
import com.binas.yak.ui.others.drawing.interactor.DrawingInteractor
import com.binas.yak.ui.others.drawing.view.DrawingView

interface DrawingPresenter<V: DrawingView, I: DrawingInteractor>: Presenter<V, I> {
}