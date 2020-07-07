package com.binas.yak.ui.settings.changeLimit.interactor

import com.binas.yak.ui.base.interactor.Interactor

interface ChangeLimitInteractor: Interactor {

    fun setDailyCardsLimit(limit: Int)

    fun getDailyCardsLimit(): Int
}