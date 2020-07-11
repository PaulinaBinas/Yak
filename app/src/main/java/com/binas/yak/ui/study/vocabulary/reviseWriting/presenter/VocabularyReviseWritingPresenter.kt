package com.binas.yak.ui.study.vocabulary.reviseWriting.presenter

import com.binas.yak.ui.base.presenter.Presenter
import com.binas.yak.ui.study.vocabulary.reviseWriting.interactor.VocabularyReviseWritingInteractor
import com.binas.yak.ui.study.vocabulary.reviseWriting.view.VocabularyReviseWritingView

interface VocabularyReviseWritingPresenter<V: VocabularyReviseWritingView, I: VocabularyReviseWritingInteractor>: Presenter<V, I> {

    fun start(id: Long)
}