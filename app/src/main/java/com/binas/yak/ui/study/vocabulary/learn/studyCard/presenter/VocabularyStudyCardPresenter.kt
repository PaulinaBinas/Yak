package com.binas.yak.ui.study.vocabulary.learn.studyCard.presenter

import com.binas.yak.ui.base.presenter.Presenter
import com.binas.yak.ui.study.vocabulary.learn.studyCard.interactor.VocabularyStudyCardInteractor
import com.binas.yak.ui.study.vocabulary.learn.studyCard.view.VocabularyStudyCardView

interface VocabularyStudyCardPresenter<V: VocabularyStudyCardView, I: VocabularyStudyCardInteractor>: Presenter<V, I> {

    fun start()
}