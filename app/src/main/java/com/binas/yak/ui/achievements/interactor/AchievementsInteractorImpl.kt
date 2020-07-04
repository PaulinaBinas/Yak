package com.binas.yak.ui.achievements.interactor

import com.binas.yak.data.model.achievements.AchievementsRepository
import com.binas.yak.data.preferences.PreferenceHelper
import com.binas.yak.ui.base.interactor.BaseInteractor
import javax.inject.Inject

class AchievementsInteractorImpl @Inject internal constructor(preferenceHelper: PreferenceHelper, var achievementsRepo: AchievementsRepository):
    BaseInteractor(preferenceHelper), AchievementsInteractor {

    override fun getSignsStudied(): Int {
        return achievementsRepo.getStudiedSignsCount()
    }

    override fun getSignsTotal(): Int {
        return achievementsRepo.getSignsCount()
    }

    override fun getWordsStudied(): Int {
        return achievementsRepo.getStudiedWordsCount()
    }

    override fun getWordsTotal(): Int {
        return achievementsRepo.getWordsCount()
    }

    override fun getGrammarStudied(): Int {
        return achievementsRepo.getStudiedGrammarCount()
    }

    override fun getGrammarTotal(): Int {
        return achievementsRepo.getGrammarCount()
    }
}