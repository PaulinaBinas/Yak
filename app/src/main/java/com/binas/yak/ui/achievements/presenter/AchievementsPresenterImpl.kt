package com.binas.yak.ui.achievements.presenter

import com.binas.yak.ui.achievements.interactor.AchievementsInteractor
import com.binas.yak.ui.achievements.view.AchievementsView
import com.binas.yak.ui.base.presenter.BasePresenter
import javax.inject.Inject

class AchievementsPresenterImpl<V: AchievementsView, I: AchievementsInteractor>
@Inject internal constructor(interactor: I): BasePresenter<V, I>(interactor = interactor), AchievementsPresenter<V, I> {
}