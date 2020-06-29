package com.binas.yak.ui.study.grammar.reviseSound.presenter

import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.study.grammar.reviseSound.interactor.GrammarReviseSoundInteractor
import com.binas.yak.ui.study.grammar.reviseSound.view.GrammarReviseSoundView
import javax.inject.Inject

class GrammarReviseSoundPresenterImpl<V: GrammarReviseSoundView, I: GrammarReviseSoundInteractor>
@Inject internal constructor(interactor: I): BasePresenter<V, I>(interactor = interactor), GrammarReviseSoundPresenter<V, I> {
}