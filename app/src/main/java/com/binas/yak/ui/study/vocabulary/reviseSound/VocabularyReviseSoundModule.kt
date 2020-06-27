package com.binas.yak.ui.study.vocabulary.reviseSound

import com.binas.yak.ui.study.vocabulary.reviseSound.interactor.VocabularyReviseSoundInteractor
import com.binas.yak.ui.study.vocabulary.reviseSound.interactor.VocabularyReviseSoundInteractorImpl
import com.binas.yak.ui.study.vocabulary.reviseSound.presenter.VocabularyReviseSoundPresenter
import com.binas.yak.ui.study.vocabulary.reviseSound.presenter.VocabularyReviseSoundPresenterImpl
import com.binas.yak.ui.study.vocabulary.reviseSound.view.VocabularyReviseSoundView
import dagger.Module
import dagger.Provides

@Module
class VocabularyReviseSoundModule {

    @Provides
    internal fun provideVocabularyReviseSoundInteractor(interactor: VocabularyReviseSoundInteractorImpl): VocabularyReviseSoundInteractor = interactor

    @Provides
    internal fun provideVocabularyReviseSoundPresenter(presenter: VocabularyReviseSoundPresenterImpl<VocabularyReviseSoundView, VocabularyReviseSoundInteractor>)
            : VocabularyReviseSoundPresenter<VocabularyReviseSoundView, VocabularyReviseSoundInteractor> = presenter
}