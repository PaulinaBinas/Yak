package com.binas.yak.ui.study.vocabulary.reviseSound.presenter

import com.binas.yak.ui.base.presenter.Presenter
import com.binas.yak.ui.study.vocabulary.reviseSound.interactor.VocabularyReviseSoundInteractor
import com.binas.yak.ui.study.vocabulary.reviseSound.view.VocabularyReviseSoundView

interface VocabularyReviseSoundPresenter<V: VocabularyReviseSoundView, I: VocabularyReviseSoundInteractor>: Presenter<V, I> {
}