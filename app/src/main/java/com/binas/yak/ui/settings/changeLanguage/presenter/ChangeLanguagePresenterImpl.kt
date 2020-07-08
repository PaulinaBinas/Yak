package com.binas.yak.ui.settings.changeLanguage.presenter

import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.settings.changeLanguage.interactor.ChangeLanguageInteractor
import com.binas.yak.ui.settings.changeLanguage.view.ChangeLanguageView
import javax.inject.Inject

class ChangeLanguagePresenterImpl<V: ChangeLanguageView, I: ChangeLanguageInteractor> @Inject internal constructor(interactor: I): BasePresenter<V, I>(interactor = interactor), ChangeLanguagePresenter<V, I> {

    override fun changeLanguageIsSet() {
        interactor?.let {
            it.setLanguageIsSet()
        }
    }
}