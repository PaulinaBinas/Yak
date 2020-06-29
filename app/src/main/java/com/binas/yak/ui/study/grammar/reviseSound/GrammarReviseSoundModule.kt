package com.binas.yak.ui.study.grammar.reviseSound

import com.binas.yak.ui.study.grammar.reviseSound.interactor.GrammarReviseSoundInteractor
import com.binas.yak.ui.study.grammar.reviseSound.interactor.GrammarReviseSoundInteractorImpl
import com.binas.yak.ui.study.grammar.reviseSound.presenter.GrammarReviseSoundPresenter
import com.binas.yak.ui.study.grammar.reviseSound.presenter.GrammarReviseSoundPresenterImpl
import com.binas.yak.ui.study.grammar.reviseSound.view.GrammarReviseSoundView
import dagger.Module
import dagger.Provides

@Module
class GrammarReviseSoundModule {

    @Provides
    internal fun provideGrammarReviseSoundInteractor(interactor: GrammarReviseSoundInteractorImpl): GrammarReviseSoundInteractor = interactor

    @Provides
    internal fun provideGrammarReviseSoundPresenter(presenter: GrammarReviseSoundPresenterImpl<GrammarReviseSoundView, GrammarReviseSoundInteractor>)
            : GrammarReviseSoundPresenter<GrammarReviseSoundView, GrammarReviseSoundInteractor> = presenter
}