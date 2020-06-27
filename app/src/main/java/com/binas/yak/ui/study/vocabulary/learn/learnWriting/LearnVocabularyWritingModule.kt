package com.binas.yak.ui.study.vocabulary.learn.learnWriting

import com.binas.yak.ui.study.vocabulary.learn.learnWriting.interactor.LearnVocabularyWritingInteractor
import com.binas.yak.ui.study.vocabulary.learn.learnWriting.interactor.LearnVocabularyWritingInteractorImpl
import com.binas.yak.ui.study.vocabulary.learn.learnWriting.presenter.LearnVocabularyWritingPresenter
import com.binas.yak.ui.study.vocabulary.learn.learnWriting.presenter.LearnVocabularyWritingPresenterImpl
import com.binas.yak.ui.study.vocabulary.learn.learnWriting.view.LearnVocabularyWritingView
import dagger.Module
import dagger.Provides

@Module
class LearnVocabularyWritingModule {

    @Provides
    internal fun provideLearnVocabularyWriting(interactor: LearnVocabularyWritingInteractorImpl): LearnVocabularyWritingInteractor = interactor

    @Provides
    internal fun provideLearnVocabularyWritingPresenter(presenter: LearnVocabularyWritingPresenterImpl<LearnVocabularyWritingView, LearnVocabularyWritingInteractor>)
            : LearnVocabularyWritingPresenter<LearnVocabularyWritingView, LearnVocabularyWritingInteractor> = presenter
}