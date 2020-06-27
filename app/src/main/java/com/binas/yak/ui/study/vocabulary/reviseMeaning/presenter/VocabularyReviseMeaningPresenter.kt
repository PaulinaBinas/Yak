package com.binas.yak.ui.study.vocabulary.reviseMeaning.presenter

import com.binas.yak.ui.base.presenter.Presenter
import com.binas.yak.ui.study.vocabulary.reviseMeaning.interactor.VocabularyReviseMeaningInteractor
import com.binas.yak.ui.study.vocabulary.reviseMeaning.view.VocabularyReviseMeaningView

interface VocabularyReviseMeaningPresenter<V: VocabularyReviseMeaningView, I: VocabularyReviseMeaningInteractor>: Presenter<V, I> {
}