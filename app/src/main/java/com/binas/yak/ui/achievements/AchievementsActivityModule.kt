package com.binas.yak.ui.achievements

import com.binas.yak.ui.achievements.interactor.AchievementsInteractor
import com.binas.yak.ui.achievements.interactor.AchievementsInteractorImpl
import com.binas.yak.ui.achievements.presenter.AchievementsPresenter
import com.binas.yak.ui.achievements.presenter.AchievementsPresenterImpl
import com.binas.yak.ui.achievements.view.AchievementsView
import dagger.Module
import dagger.Provides

@Module
class AchievementsActivityModule {

    @Provides
    internal fun provideAchievementsInteractor(interactor: AchievementsInteractorImpl)
            : AchievementsInteractor = interactor

    @Provides
    internal fun provideAchievementsPresenter(presenter: AchievementsPresenterImpl<AchievementsView,
            AchievementsInteractor>)
            : AchievementsPresenter<AchievementsView, AchievementsInteractor> = presenter
}