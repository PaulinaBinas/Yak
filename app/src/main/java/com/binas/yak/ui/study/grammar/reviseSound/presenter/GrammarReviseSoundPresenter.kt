package com.binas.yak.ui.study.grammar.reviseSound.presenter

import com.binas.yak.ui.base.presenter.Presenter
import com.binas.yak.ui.study.grammar.reviseSound.interactor.GrammarReviseSoundInteractor
import com.binas.yak.ui.study.grammar.reviseSound.view.GrammarReviseSoundView

interface GrammarReviseSoundPresenter<V: GrammarReviseSoundView, I: GrammarReviseSoundInteractor>: Presenter<V, I> {
}