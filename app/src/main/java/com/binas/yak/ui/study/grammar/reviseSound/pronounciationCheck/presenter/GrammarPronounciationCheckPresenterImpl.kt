package com.binas.yak.ui.study.grammar.reviseSound.pronounciationCheck.presenter

import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.study.grammar.reviseSound.pronounciationCheck.interactor.GrammarPronounciationCheckInteractor
import com.binas.yak.ui.study.grammar.reviseSound.pronounciationCheck.view.GrammarPronounciationCheckView
import javax.inject.Inject

class GrammarPronounciationCheckPresenterImpl<V: GrammarPronounciationCheckView, I: GrammarPronounciationCheckInteractor>
@Inject internal constructor(interactor: I): BasePresenter<V, I>(interactor = interactor), GrammarPronounciationCheckPresenter<V, I> {
}