package com.binas.yak.ui.others.drawing.presenter

import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.others.drawing.interactor.DrawingInteractor
import com.binas.yak.ui.others.drawing.view.DrawingView
import javax.inject.Inject

class DrawingPresenterImpl<V: DrawingView, I: DrawingInteractor>
@Inject internal constructor(interactor: I): BasePresenter<V, I>(interactor = interactor), DrawingPresenter<V, I> {
}