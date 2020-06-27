package com.binas.yak.ui.study.vocabulary.reviseMeaning

import com.binas.yak.ui.study.vocabulary.reviseMeaning.interactor.VocabularyReviseMeaningInteractor
import com.binas.yak.ui.study.vocabulary.reviseMeaning.interactor.VocabularyReviseMeaningInteractorImpl
import com.binas.yak.ui.study.vocabulary.reviseMeaning.presenter.VocabularyReviseMeaningPresenter
import com.binas.yak.ui.study.vocabulary.reviseMeaning.presenter.VocabularyReviseMeaningPresenterImpl
import com.binas.yak.ui.study.vocabulary.reviseMeaning.view.VocabularyReviseMeaningView
import dagger.Module
import dagger.Provides

@Module
class VocabularyReviseMeaningModule {

    @Provides
    internal fun provideVocabularyReviseMeaningInteractor(interactor: VocabularyReviseMeaningInteractorImpl): VocabularyReviseMeaningInteractor = interactor

    @Provides
    internal fun provideVocabularyReviseMeaningPresenter(presenter: VocabularyReviseMeaningPresenterImpl<VocabularyReviseMeaningView, VocabularyReviseMeaningInteractor>)
            : VocabularyReviseMeaningPresenter<VocabularyReviseMeaningView, VocabularyReviseMeaningInteractor> = presenter
}