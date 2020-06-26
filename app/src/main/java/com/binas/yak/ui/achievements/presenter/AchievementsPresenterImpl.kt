package com.binas.yak.ui.achievements.presenter

import com.binas.yak.ui.achievements.interactor.AchievementsInteractor
import com.binas.yak.ui.achievements.view.AchievementsView
import javax.inject.Inject

class AchievementsPresenterImpl<V: AchievementsView, I: AchievementsInteractor>
@Inject internal constructor(interactor: I): AchievementsPresenter<V, I>(interactor = interactor) {
}