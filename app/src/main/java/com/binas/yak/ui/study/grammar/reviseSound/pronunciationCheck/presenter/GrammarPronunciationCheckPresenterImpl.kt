package com.binas.yak.ui.study.grammar.reviseSound.pronunciationCheck.presenter

import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.study.grammar.reviseSound.pronunciationCheck.interactor.GrammarPronunciationCheckInteractor
import com.binas.yak.ui.study.grammar.reviseSound.pronunciationCheck.view.GrammarPronunciationCheckView
import javax.inject.Inject

class GrammarPronunciationCheckPresenterImpl<V: GrammarPronunciationCheckView, I: GrammarPronunciationCheckInteractor>
@Inject internal constructor(interactor: I): BasePresenter<V, I>(interactor = interactor), GrammarPronunciationCheckPresenter<V, I> {
}