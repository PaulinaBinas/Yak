package com.binas.yak.ui.study.grammar.learn.learnWriting

import com.binas.yak.ui.study.grammar.learn.learnWriting.interactor.LearnGrammarWritingInteractor
import com.binas.yak.ui.study.grammar.learn.learnWriting.interactor.LearnGrammarWritingInteractorImpl
import com.binas.yak.ui.study.grammar.learn.learnWriting.presenter.LearnGrammarWritingPresenter
import com.binas.yak.ui.study.grammar.learn.learnWriting.presenter.LearnGrammarWritingPresenterImpl
import com.binas.yak.ui.study.grammar.learn.learnWriting.view.LearnGrammarWritingView
import dagger.Module
import dagger.Provides

@Module
class LearnGrammarWritingModule {

    @Provides
    internal fun provideLearnGrammarWritingInteractor(interactor: LearnGrammarWritingInteractorImpl): LearnGrammarWritingInteractor = interactor

    @Provides
    internal fun provideLearnGrammarWritingPresenter(presenter: LearnGrammarWritingPresenterImpl<LearnGrammarWritingView, LearnGrammarWritingInteractor>)
            : LearnGrammarWritingPresenter< LearnGrammarWritingView, LearnGrammarWritingInteractor> = presenter
}