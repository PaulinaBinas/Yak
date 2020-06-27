package com.binas.yak.ui.study.vocabulary.learn.studyCard

import com.binas.yak.ui.study.vocabulary.learn.studyCard.interactor.VocabularyStudyCardInteractor
import com.binas.yak.ui.study.vocabulary.learn.studyCard.interactor.VocabularyStudyCardInteractorImpl
import com.binas.yak.ui.study.vocabulary.learn.studyCard.presenter.VocabularyStudyCardPresenter
import com.binas.yak.ui.study.vocabulary.learn.studyCard.presenter.VocabularyStudyCardPresenterImpl
import com.binas.yak.ui.study.vocabulary.learn.studyCard.view.VocabularyStudyCardView
import dagger.Module
import dagger.Provides

@Module
class VocabularyStudyCardModule {

    @Provides
    internal fun provideVocabularyStudyCardInteractor(interactor: VocabularyStudyCardInteractorImpl): VocabularyStudyCardInteractor = interactor

    @Provides
    internal fun provideVocabularyStudyCardPresenter(presenter: VocabularyStudyCardPresenterImpl<VocabularyStudyCardView, VocabularyStudyCardInteractor>): VocabularyStudyCardPresenter<VocabularyStudyCardView, VocabularyStudyCardInteractor> = presenter
}