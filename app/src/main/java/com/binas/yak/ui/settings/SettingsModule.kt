package com.binas.yak.ui.settings

import com.binas.yak.ui.settings.interactor.SettingsInteractor
import com.binas.yak.ui.settings.interactor.SettingsInteractorImpl
import com.binas.yak.ui.settings.presenter.SettingsPresenter
import com.binas.yak.ui.settings.presenter.SettingsPresenterImpl
import com.binas.yak.ui.settings.view.SettingsView
import dagger.Module
import dagger.Provides

@Module
class SettingsModule {

    @Provides
    internal fun provideSettingsInteractor(interactor: SettingsInteractorImpl): SettingsInteractor = interactor

    @Provides
    internal fun provideSettingsPresenter(presenter: SettingsPresenterImpl<SettingsView, SettingsInteractor>)
            : SettingsPresenter<SettingsView, SettingsInteractor> = presenter
}