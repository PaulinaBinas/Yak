package com.binas.yak.ui.study.grammar.learn.learnWriting.presenter

import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.study.grammar.learn.learnWriting.interactor.LearnGrammarWritingInteractor
import com.binas.yak.ui.study.grammar.learn.learnWriting.view.LearnGrammarWritingView
import javax.inject.Inject

class LearnGrammarWritingPresenterImpl<V: LearnGrammarWritingView, I: LearnGrammarWritingInteractor>
@Inject internal constructor(interactor: I): BasePresenter<V, I>(interactor = interactor), LearnGrammarWritingPresenter<V, I>{
}