package com.binas.yak.ui.study.grammar.reviseSound.pronounciationCheck

import com.binas.yak.ui.study.grammar.reviseSound.pronounciationCheck.interactor.GrammarPronounciationCheckInteractor
import com.binas.yak.ui.study.grammar.reviseSound.pronounciationCheck.interactor.GrammarPronounciationCheckInteractorImpl
import com.binas.yak.ui.study.grammar.reviseSound.pronounciationCheck.presenter.GrammarPronounciationCheckPresenter
import com.binas.yak.ui.study.grammar.reviseSound.pronounciationCheck.presenter.GrammarPronounciationCheckPresenterImpl
import com.binas.yak.ui.study.grammar.reviseSound.pronounciationCheck.view.GrammarPronounciationCheckView
import dagger.Module
import dagger.Provides

@Module
class GrammarPronounciationCheckModule {

    @Provides
    internal fun provideGrammarPronounciationCheckInteractor(interactor: GrammarPronounciationCheckInteractorImpl): GrammarPronounciationCheckInteractor = interactor

    @Provides
    internal fun provideGrammarPronounciationCheckPresenter(presenter: GrammarPronounciationCheckPresenterImpl<GrammarPronounciationCheckView, GrammarPronounciationCheckInteractor>)
            : GrammarPronounciationCheckPresenter<GrammarPronounciationCheckView, GrammarPronounciationCheckInteractor> = presenter
}