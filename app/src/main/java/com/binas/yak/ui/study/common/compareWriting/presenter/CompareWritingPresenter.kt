package com.binas.yak.ui.study.common.compareWriting.presenter

import com.binas.yak.ui.base.presenter.Presenter
import com.binas.yak.ui.study.common.compareWriting.interactor.CompareWritingInteractor
import com.binas.yak.ui.study.common.compareWriting.view.CompareWritingView

interface CompareWritingPresenter<V: CompareWritingView, I: CompareWritingInteractor>: Presenter<V, I> {
}