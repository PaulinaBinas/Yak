package com.binas.yak.ui.study.grammar.learn.studyCard.presenter

import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.study.grammar.learn.studyCard.interactor.GrammarStudyCardInteractor
import com.binas.yak.ui.study.grammar.learn.studyCard.view.GrammarStudyCardView
import javax.inject.Inject

class GrammarStudyCardPresenterImpl<V: GrammarStudyCardView, I: GrammarStudyCardInteractor>
@Inject internal constructor(interactor: I): BasePresenter<V, I>(interactor = interactor), GrammarStudyCardPresenter<V, I> {
}