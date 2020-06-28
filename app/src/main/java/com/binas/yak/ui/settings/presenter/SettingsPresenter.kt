package com.binas.yak.ui.settings.presenter

import com.binas.yak.ui.base.presenter.Presenter
import com.binas.yak.ui.settings.interactor.SettingsInteractor
import com.binas.yak.ui.settings.view.SettingsView

interface SettingsPresenter<V: SettingsView, I: SettingsInteractor>: Presenter<V, I> {
}