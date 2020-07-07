package com.binas.yak.ui.settings.changeLimit

import com.binas.yak.data.preferences.PreferenceHelper
import com.binas.yak.data.preferences.PreferenceHelperImpl
import com.binas.yak.ui.settings.changeLimit.interactor.ChangeLimitInteractor
import com.binas.yak.ui.settings.changeLimit.interactor.ChangeLimitInteractorImpl
import com.binas.yak.ui.settings.changeLimit.presenter.ChangeLimitPresenter
import com.binas.yak.ui.settings.changeLimit.presenter.ChangeLimitPresenterImpl
import com.binas.yak.ui.settings.changeLimit.view.ChangeLimitView
import dagger.Module
import dagger.Provides

@Module
class ChangeLimitModule {

    @Provides
    internal fun provideChangeLimitInteractor(interactor: ChangeLimitInteractorImpl): ChangeLimitInteractor = interactor

    @Provides
    internal fun provideChangeLimitPresenter(presenter: ChangeLimitPresenterImpl<ChangeLimitView, ChangeLimitInteractor>): ChangeLimitPresenter<ChangeLimitView, ChangeLimitInteractor> = presenter
}