package com.binas.yak.ui.study.grammar.reviseSound.presenter

import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.study.grammar.reviseSound.interactor.GrammarReviseSoundInteractor
import com.binas.yak.ui.study.grammar.reviseSound.view.GrammarReviseSoundView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class GrammarReviseSoundPresenterImpl<V: GrammarReviseSoundView, I: GrammarReviseSoundInteractor>
@Inject internal constructor(interactor: I): BasePresenter<V, I>(interactor = interactor), GrammarReviseSoundPresenter<V, I> {

    override fun start() {
        interactor?.let {
            var coroutine = GlobalScope.launch {
                var card = it.getGrammarRevisionFlashcard(1)
                var grammar = it.getGrammar(card.grammarId)
                var translation = grammar.translationId?.let { it1 -> it.getTranslation(it1) }
                getView()?.setContent(card, grammar, translation)
                getView()?.loadText()
            }
            while(!coroutine.isCompleted){}
        }
    }
}