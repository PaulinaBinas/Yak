package com.binas.yak.ui.study.grammar.reviseWriting.presenter

import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.study.grammar.reviseWriting.interactor.GrammarReviseWritingInteractor
import com.binas.yak.ui.study.grammar.reviseWriting.view.GrammarReviseWritingView
import javax.inject.Inject

class GrammarReviseWritingPresenterImpl<V: GrammarReviseWritingView, I: GrammarReviseWritingInteractor>
@Inject internal constructor(interactor: I): BasePresenter<V, I>(interactor = interactor),
    GrammarReviseWritingPresenter<V, I> {
    
}