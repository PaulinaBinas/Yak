package com.binas.yak.ui.study.grammar.reviseSound.pronounciationCheck.presenter

import com.binas.yak.ui.base.presenter.Presenter
import com.binas.yak.ui.study.grammar.reviseSound.pronounciationCheck.interactor.GrammarPronounciationCheckInteractor
import com.binas.yak.ui.study.grammar.reviseSound.pronounciationCheck.view.GrammarPronounciationCheckView

interface GrammarPronounciationCheckPresenter<V: GrammarPronounciationCheckView, I: GrammarPronounciationCheckInteractor>: Presenter<V, I> {
}