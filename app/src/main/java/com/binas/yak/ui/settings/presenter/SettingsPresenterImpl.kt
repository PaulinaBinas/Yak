package com.binas.yak.ui.settings.presenter

import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.settings.interactor.SettingsInteractor
import com.binas.yak.ui.settings.view.SettingsView
import javax.inject.Inject

class SettingsPresenterImpl<V: SettingsView, I: SettingsInteractor>
@Inject internal constructor(interactor: I): BasePresenter<V, I>(interactor = interactor), SettingsPresenter<V, I> {
}