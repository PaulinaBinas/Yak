package com.binas.yak.ui.settings.changeLimit.presenter

import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.settings.changeLimit.interactor.ChangeLimitInteractor
import com.binas.yak.ui.settings.changeLimit.view.ChangeLimitView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChangeLimitPresenterImpl<V: ChangeLimitView, I: ChangeLimitInteractor>
@Inject constructor(interactor: I): BasePresenter<V, I>(interactor = interactor), ChangeLimitPresenter<V, I> {

    override fun start() {
        interactor?.let {
            GlobalScope.launch {
                var number = it.getDailyCardsLimit()
                getView()?.setNumberOfCards(number)
            }
        }
    }

    override fun changeDailyCardsLimit(limit: Int) {
        interactor?.let {
            GlobalScope.launch {
                it.setDailyCardsLimit(limit)
            }
        }
    }

}