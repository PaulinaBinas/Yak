package com.binas.yak.ui.study.vocabulary.reviseWriting.presenter

import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.study.vocabulary.reviseWriting.interactor.VocabularyReviseWritingInteractor
import com.binas.yak.ui.study.vocabulary.reviseWriting.view.VocabularyReviseWritingView
import javax.inject.Inject

class VocabularyReviseWritingPresenterImpl<V: VocabularyReviseWritingView, I: VocabularyReviseWritingInteractor>
@Inject internal constructor(interactor: I): BasePresenter<V, I>(interactor = interactor), VocabularyReviseWritingPresenter<V, I> {
}