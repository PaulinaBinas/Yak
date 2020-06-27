package com.binas.yak.ui.study.sign.reviseWriting.presenter

import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.study.sign.reviseWriting.interactor.SignReviseWritingInteractor
import com.binas.yak.ui.study.sign.reviseWriting.view.SignReviseWritingView
import javax.inject.Inject

class SignReviseWritingPresenterImpl<V: SignReviseWritingView, I: SignReviseWritingInteractor>
    @Inject internal constructor(interactor: I): BasePresenter<V, I>(interactor = interactor),
    SignReviseWritingPresenter<V, I> {
}