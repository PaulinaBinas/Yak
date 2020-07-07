package com.binas.yak.ui.settings.changeLimit.presenter

import com.binas.yak.ui.base.presenter.Presenter
import com.binas.yak.ui.settings.changeLimit.interactor.ChangeLimitInteractor
import com.binas.yak.ui.settings.changeLimit.view.ChangeLimitView

interface ChangeLimitPresenter<V: ChangeLimitView, I: ChangeLimitInteractor>: Presenter<V, I> {

    fun start()

    fun changeDailyCardsLimit(limit: Int)
}