package com.binas.yak.ui.study.grammar.reviseSound.pronunciationCheck

import com.binas.yak.ui.study.grammar.reviseSound.pronunciationCheck.interactor.GrammarPronunciationCheckInteractor
import com.binas.yak.ui.study.grammar.reviseSound.pronunciationCheck.interactor.GrammarPronunciationCheckInteractorImpl
import com.binas.yak.ui.study.grammar.reviseSound.pronunciationCheck.presenter.GrammarPronunciationCheckPresenter
import com.binas.yak.ui.study.grammar.reviseSound.pronunciationCheck.presenter.GrammarPronunciationCheckPresenterImpl
import com.binas.yak.ui.study.grammar.reviseSound.pronunciationCheck.view.GrammarPronunciationCheckView
import dagger.Module
import dagger.Provides

@Module
class GrammarPronunciationCheckModule {

    @Provides
    internal fun provideGrammarPronunciationCheckInteractor(interactor: GrammarPronunciationCheckInteractorImpl): GrammarPronunciationCheckInteractor = interactor

    @Provides
    internal fun provideGrammarPronunciationCheckPresenter(presenter: GrammarPronunciationCheckPresenterImpl<GrammarPronunciationCheckView, GrammarPronunciationCheckInteractor>)
            : GrammarPronunciationCheckPresenter<GrammarPronunciationCheckView, GrammarPronunciationCheckInteractor> = presenter
}