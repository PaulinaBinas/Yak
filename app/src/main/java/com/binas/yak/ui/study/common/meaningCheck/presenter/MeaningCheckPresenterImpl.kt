package com.binas.yak.ui.study.common.meaningCheck.presenter

import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.study.common.meaningCheck.interactor.MeaningCheckInteractor
import com.binas.yak.ui.study.common.meaningCheck.view.MeaningCheckView
import javax.inject.Inject

class MeaningCheckPresenterImpl<V: MeaningCheckView, I: MeaningCheckInteractor>
@Inject internal constructor(interactor: I): BasePresenter<V, I>(interactor = interactor), MeaningCheckPresenter<V, I> {
}