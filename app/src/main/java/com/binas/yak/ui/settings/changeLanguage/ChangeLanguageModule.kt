package com.binas.yak.ui.settings.changeLanguage

import com.binas.yak.ui.settings.changeLanguage.interactor.ChangeLanguageInteractor
import com.binas.yak.ui.settings.changeLanguage.interactor.ChangeLanguageInteractorImpl
import com.binas.yak.ui.settings.changeLanguage.presenter.ChangeLanguagePresenter
import com.binas.yak.ui.settings.changeLanguage.presenter.ChangeLanguagePresenterImpl
import com.binas.yak.ui.settings.changeLanguage.view.ChangeLanguageView
import dagger.Module
import dagger.Provides

@Module
class ChangeLanguageModule {

    @Provides
    internal fun provideChangeLanguageInteractor(interactor: ChangeLanguageInteractorImpl): ChangeLanguageInteractor = interactor

    @Provides
    internal fun provideChangeLanguagePresenter(presenter: ChangeLanguagePresenterImpl<ChangeLanguageView, ChangeLanguageInteractor>)
            : ChangeLanguagePresenter<ChangeLanguageView, ChangeLanguageInteractor> = presenter
}