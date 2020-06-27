package com.binas.yak.ui.study.vocabulary.reviseSound.presenter

import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.study.vocabulary.reviseSound.interactor.VocabularyReviseSoundInteractor
import com.binas.yak.ui.study.vocabulary.reviseSound.view.VocabularyReviseSoundView
import javax.inject.Inject

class VocabularyReviseSoundPresenterImpl<V: VocabularyReviseSoundView, I: VocabularyReviseSoundInteractor>
@Inject internal constructor(interactor: I): BasePresenter<V, I>(interactor = interactor), VocabularyReviseSoundPresenter<V, I> {
}