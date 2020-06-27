package com.binas.yak.ui.achievements.interactor

import com.binas.yak.data.preferences.PreferenceHelper
import com.binas.yak.ui.base.interactor.BaseInteractor
import javax.inject.Inject

class AchievementsInteractorImpl @Inject internal constructor(preferenceHelper: PreferenceHelper):
    BaseInteractor(preferenceHelper), AchievementsInteractor {
}