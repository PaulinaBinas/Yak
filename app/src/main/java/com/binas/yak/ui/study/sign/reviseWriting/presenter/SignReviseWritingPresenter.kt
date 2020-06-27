package com.binas.yak.ui.study.sign.reviseWriting.presenter

import com.binas.yak.ui.base.presenter.Presenter
import com.binas.yak.ui.study.sign.reviseWriting.interactor.SignReviseWritingInteractor
import com.binas.yak.ui.study.sign.reviseWriting.view.SignReviseWritingView

interface SignReviseWritingPresenter<V: SignReviseWritingView, I: SignReviseWritingInteractor>: Presenter<V, I> {
}