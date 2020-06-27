package com.binas.yak.ui.study.sign.reviseWithDecision.presenter

import com.binas.yak.ui.base.presenter.Presenter
import com.binas.yak.ui.study.sign.reviseWithDecision.interactor.SignReviseWithDecisionInteractor
import com.binas.yak.ui.study.sign.reviseWithDecision.view.SignReviseWithDecisionView

interface SignReviseWithDecisionPresenter<V: SignReviseWithDecisionView, I: SignReviseWithDecisionInteractor>: Presenter<V, I> {
}