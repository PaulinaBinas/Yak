package com.binas.yak.ui.study.grammar.learn.studyCard

import com.binas.yak.ui.study.grammar.learn.studyCard.interactor.GrammarStudyCardInteractor
import com.binas.yak.ui.study.grammar.learn.studyCard.interactor.GrammarStudyCardInteractorImpl
import com.binas.yak.ui.study.grammar.learn.studyCard.presenter.GrammarStudyCardPresenter
import com.binas.yak.ui.study.grammar.learn.studyCard.presenter.GrammarStudyCardPresenterImpl
import com.binas.yak.ui.study.grammar.learn.studyCard.view.GrammarStudyCardView
import dagger.Module
import dagger.Provides

@Module
class GrammarStudyCardModule {

    @Provides
    internal fun provideGrammarStudyCardInteractor(interactor: GrammarStudyCardInteractorImpl): GrammarStudyCardInteractor = interactor

    @Provides
    internal fun provideGrammarStudyCardPresenter(presenter: GrammarStudyCardPresenterImpl<GrammarStudyCardView, GrammarStudyCardInteractor>)
            : GrammarStudyCardPresenter<GrammarStudyCardView, GrammarStudyCardInteractor> = presenter
}