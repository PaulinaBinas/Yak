package com.binas.yak.ui.study.sign.reviseSound.presenter

import com.binas.yak.ui.base.presenter.Presenter
import com.binas.yak.ui.study.sign.reviseSound.interactor.SignReviseSoundInteractor
import com.binas.yak.ui.study.sign.reviseSound.view.SignReviseSoundView

interface SignReviseSoundPresenter<V: SignReviseSoundView, I: SignReviseSoundInteractor>: Presenter<V, I> {

    fun start(id: Long)
}