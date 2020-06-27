package com.binas.yak.ui.study.vocabulary.reviseWriting

import com.binas.yak.ui.study.vocabulary.reviseWriting.interactor.VocabularyReviseWritingInteractor
import com.binas.yak.ui.study.vocabulary.reviseWriting.interactor.VocabularyReviseWritinginteractorImpl
import com.binas.yak.ui.study.vocabulary.reviseWriting.presenter.VocabularyReviseWritingPresenter
import com.binas.yak.ui.study.vocabulary.reviseWriting.presenter.VocabularyReviseWritingPresenterImpl
import com.binas.yak.ui.study.vocabulary.reviseWriting.view.VocabularyReviseWritingView
import dagger.Module
import dagger.Provides

@Module
class VocabularyReviseWritingModule {

    @Provides
    internal fun provideVocabularyReviseWritingInteractor(interactor: VocabularyReviseWritinginteractorImpl): VocabularyReviseWritingInteractor = interactor

    @Provides
    internal fun provideVocabularyReviseWritingPresenter(presenter: VocabularyReviseWritingPresenterImpl<VocabularyReviseWritingView, VocabularyReviseWritingInteractor>)
            : VocabularyReviseWritingPresenter<VocabularyReviseWritingView, VocabularyReviseWritingInteractor> = presenter
}