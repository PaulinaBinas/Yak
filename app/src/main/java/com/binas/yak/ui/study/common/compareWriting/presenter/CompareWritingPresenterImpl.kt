package com.binas.yak.ui.study.common.compareWriting.presenter

import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.study.common.compareWriting.interactor.CompareWritingInteractor
import com.binas.yak.ui.study.common.compareWriting.view.CompareWritingView
import javax.inject.Inject

class CompareWritingPresenterImpl<V: CompareWritingView, I: CompareWritingInteractor>
@Inject internal constructor(interactor: I): BasePresenter<V, I>(interactor = interactor),CompareWritingPresenter<V, I> {
}