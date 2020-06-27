package com.binas.yak.ui.study.vocabulary.reviseMeaning.presenter

import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.study.vocabulary.reviseMeaning.interactor.VocabularyReviseMeaningInteractor
import com.binas.yak.ui.study.vocabulary.reviseMeaning.view.VocabularyReviseMeaningView
import javax.inject.Inject

class VocabularyReviseMeaningPresenterImpl<V: VocabularyReviseMeaningView, I: VocabularyReviseMeaningInteractor>
@Inject internal constructor(interactor: I): BasePresenter<V, I>(interactor = interactor), VocabularyReviseMeaningPresenter<V, I> {
}