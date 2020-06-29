package com.binas.yak.ui.study.grammar.reviseSound.pronunciationCheck.presenter

import com.binas.yak.ui.base.presenter.Presenter
import com.binas.yak.ui.study.grammar.reviseSound.pronunciationCheck.interactor.GrammarPronunciationCheckInteractor
import com.binas.yak.ui.study.grammar.reviseSound.pronunciationCheck.view.GrammarPronunciationCheckView

interface GrammarPronunciationCheckPresenter<V: GrammarPronunciationCheckView, I: GrammarPronunciationCheckInteractor>: Presenter<V, I> {
}