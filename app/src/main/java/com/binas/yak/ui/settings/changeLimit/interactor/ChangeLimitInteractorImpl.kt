package com.binas.yak.ui.settings.changeLimit.interactor

import com.binas.yak.data.preferences.PreferenceHelper
import javax.inject.Inject

class ChangeLimitInteractorImpl @Inject internal constructor(var preferenceHelper: PreferenceHelper): ChangeLimitInteractor {

    override fun setDailyCardsLimit(limit: Int) {
        preferenceHelper.setDailyCardLimit(limit)
    }

    override fun getDailyCardsLimit(): Int {
        return preferenceHelper.getDailyCardLimit()
    }
}