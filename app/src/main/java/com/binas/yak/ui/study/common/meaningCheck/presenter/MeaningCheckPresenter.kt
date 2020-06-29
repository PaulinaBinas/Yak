package com.binas.yak.ui.study.common.meaningCheck.presenter

import com.binas.yak.ui.base.presenter.Presenter
import com.binas.yak.ui.study.common.meaningCheck.interactor.MeaningCheckInteractor
import com.binas.yak.ui.study.common.meaningCheck.view.MeaningCheckView

interface MeaningCheckPresenter<V: MeaningCheckView, I: MeaningCheckInteractor>: Presenter<V, I> {
}