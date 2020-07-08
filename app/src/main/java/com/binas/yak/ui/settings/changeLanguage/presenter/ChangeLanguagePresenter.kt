package com.binas.yak.ui.settings.changeLanguage.presenter

import com.binas.yak.ui.base.presenter.Presenter
import com.binas.yak.ui.settings.changeLanguage.interactor.ChangeLanguageInteractor
import com.binas.yak.ui.settings.changeLanguage.view.ChangeLanguageView

interface ChangeLanguagePresenter<V: ChangeLanguageView, I: ChangeLanguageInteractor>: Presenter<V, I> {

    fun changeLanguageIsSet()
}