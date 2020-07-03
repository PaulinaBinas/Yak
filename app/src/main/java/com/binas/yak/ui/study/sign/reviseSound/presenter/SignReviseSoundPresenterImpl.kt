package com.binas.yak.ui.study.sign.reviseSound.presenter

import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.study.sign.reviseSound.interactor.SignReviseSoundInteractor
import com.binas.yak.ui.study.sign.reviseSound.view.SignReviseSoundView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignReviseSoundPresenterImpl<V: SignReviseSoundView, I: SignReviseSoundInteractor>
@Inject internal constructor(interactor: I): BasePresenter<V, I>(interactor = interactor),
    SignReviseSoundPresenter<V, I> {

    override fun start() {
        interactor?.let {
            var coroutine = GlobalScope.launch {
                var card = it.getSignRevisionCard(3)
                var sign = it.getSign(card.signId)
                getView()?.setContent(card, sign)
            }
            while(!coroutine.isCompleted) {}
            if(coroutine.isCompleted) {
                getView()?.loadImage()
            }
        }
    }
}