package com.binas.yak.ui.study.vocabulary.learn.studyCard.presenter

import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.study.vocabulary.learn.studyCard.interactor.VocabularyStudyCardInteractor
import com.binas.yak.ui.study.vocabulary.learn.studyCard.view.VocabularyStudyCardView
import javax.inject.Inject

class VocabularyStudyCardPresenterImpl<V: VocabularyStudyCardView, I: VocabularyStudyCardInteractor>
@Inject internal constructor(interactor: I): BasePresenter<V, I>(interactor = interactor), VocabularyStudyCardPresenter<V, I> {
}