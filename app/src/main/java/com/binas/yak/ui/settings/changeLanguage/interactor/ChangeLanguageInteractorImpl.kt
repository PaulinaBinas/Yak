package com.binas.yak.ui.settings.changeLanguage.interactor

import com.binas.yak.data.preferences.PreferenceHelper
import javax.inject.Inject

class ChangeLanguageInteractorImpl @Inject internal constructor(var preferenceHelper: PreferenceHelper): ChangeLanguageInteractor {

    override fun setLanguageIsSet() {
        preferenceHelper.setIsLanguageSet(true)
    }
}