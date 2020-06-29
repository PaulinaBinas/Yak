package com.binas.yak.ui.study.common.reviseWriting.presenter

import com.binas.yak.ui.base.presenter.Presenter
import com.binas.yak.ui.study.common.reviseWriting.interactor.ReviseWritingInteractor
import com.binas.yak.ui.study.common.reviseWriting.view.ReviseWritingView

interface ReviseWritingPresenter<V: ReviseWritingView, I: ReviseWritingInteractor>: Presenter<V, I> {
}