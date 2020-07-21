package com.binas.yak.ui.study.grammar.learn.studyCard.presenter

import com.binas.yak.ui.base.presenter.Presenter
import com.binas.yak.ui.study.grammar.learn.studyCard.interactor.GrammarStudyCardInteractor
import com.binas.yak.ui.study.grammar.learn.studyCard.view.GrammarStudyCardView

interface GrammarStudyCardPresenter<V: GrammarStudyCardView, I: GrammarStudyCardInteractor>: Presenter<V, I> {

    fun start(id: Long)
}