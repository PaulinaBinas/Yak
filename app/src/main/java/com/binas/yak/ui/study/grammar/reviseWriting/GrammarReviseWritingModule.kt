package com.binas.yak.ui.study.grammar.reviseWriting

import com.binas.yak.ui.study.grammar.reviseWriting.interactor.GrammarReviseWritingInteractor
import com.binas.yak.ui.study.grammar.reviseWriting.interactor.GrammarReviseWritingInteractorImpl
import com.binas.yak.ui.study.grammar.reviseWriting.presenter.GrammarReviseWritingPresenter
import com.binas.yak.ui.study.grammar.reviseWriting.presenter.GrammarReviseWritingPresenterImpl
import com.binas.yak.ui.study.grammar.reviseWriting.view.GrammarReviseWritingView
import dagger.Module
import dagger.Provides

@Module
class GrammarReviseWritingModule {

    @Provides
    internal fun provideGrammarReviseWritingInteractor(interactor: GrammarReviseWritingInteractorImpl)
            : GrammarReviseWritingInteractor = interactor

    @Provides
    internal fun provideGrammarReviseWritingPresenter(
        presenter: GrammarReviseWritingPresenterImpl<GrammarReviseWritingView, GrammarReviseWritingInteractor>)
            : GrammarReviseWritingPresenter<GrammarReviseWritingView, GrammarReviseWritingInteractor> = presenter
}