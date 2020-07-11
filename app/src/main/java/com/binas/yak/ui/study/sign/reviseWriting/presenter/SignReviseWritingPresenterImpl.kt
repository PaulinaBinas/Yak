package com.binas.yak.ui.study.sign.reviseWriting.presenter

import com.binas.yak.data.model.sign.SignRevisionFlashcard
import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.study.sign.reviseWriting.interactor.SignReviseWritingInteractor
import com.binas.yak.ui.study.sign.reviseWriting.view.SignReviseWritingView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class SignReviseWritingPresenterImpl<V: SignReviseWritingView, I: SignReviseWritingInteractor>
    @Inject internal constructor(interactor: I): BasePresenter<V, I>(interactor = interactor),
    SignReviseWritingPresenter<V, I> {

    override fun start(id: Long) {
        interactor?.let {
            var coroutine = GlobalScope.launch {
                var card = it.getSignRevisionFlashcard(id)
                var sign = it.getSign(card.signId)
                getView()?.setContent(card, sign)
            }
            while (!coroutine.isCompleted){}
            if(coroutine.isCompleted) {
                getView()?.clickSoundButton()
            }
        }
    }
}