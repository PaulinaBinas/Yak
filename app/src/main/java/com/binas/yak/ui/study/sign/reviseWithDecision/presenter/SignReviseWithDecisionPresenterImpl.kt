package com.binas.yak.ui.study.sign.reviseWithDecision.presenter

import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.study.sign.reviseWithDecision.interactor.SignReviseWithDecisionInteractor
import com.binas.yak.ui.study.sign.reviseWithDecision.view.SignReviseWithDecisionView
import javax.inject.Inject

class SignReviseWithDecisionPresenterImpl<V: SignReviseWithDecisionView, I: SignReviseWithDecisionInteractor>
    @Inject internal constructor(interactor: I): BasePresenter<V, I>(interactor = interactor), SignReviseWithDecisionPresenter<V, I> {

}