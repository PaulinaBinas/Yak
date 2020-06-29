package com.binas.yak.ui.study.common.reviseWriting.presenter

import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.study.common.reviseWriting.interactor.ReviseWritingInteractor
import com.binas.yak.ui.study.common.reviseWriting.view.ReviseWritingView
import javax.inject.Inject

class ReviseWritingPresenterImpl<V: ReviseWritingView, I: ReviseWritingInteractor>
@Inject internal constructor(interactor: I): BasePresenter<V, I>(interactor = interactor), ReviseWritingPresenter<V, I> {
}