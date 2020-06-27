package com.binas.yak.ui.study.sign.reviseSound.presenter

import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.study.sign.reviseSound.interactor.SignReviseSoundInteractor
import com.binas.yak.ui.study.sign.reviseSound.view.SignReviseSoundView
import javax.inject.Inject

class SignReviseSoundPresenterImpl<V: SignReviseSoundView, I: SignReviseSoundInteractor>
@Inject internal constructor(interactor: I): BasePresenter<V, I>(interactor = interactor),
    SignReviseSoundPresenter<V, I> {
}