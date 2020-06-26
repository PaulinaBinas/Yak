package com.binas.yak.ui.achievements.presenter

import com.binas.yak.ui.achievements.interactor.AchievementsInteractor
import com.binas.yak.ui.achievements.view.AchievementsView
import com.binas.yak.ui.base.presenter.BasePresenter

interface AchievementsPresenter<V: AchievementsView, I: AchievementsInteractor>:
    BasePresenter<V, I> {
}