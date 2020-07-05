package com.binas.yak.ui.study.grammar.reviseWriting.presenter

import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.study.grammar.reviseWriting.interactor.GrammarReviseWritingInteractor
import com.binas.yak.ui.study.grammar.reviseWriting.view.GrammarReviseWritingView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class GrammarReviseWritingPresenterImpl<V: GrammarReviseWritingView, I: GrammarReviseWritingInteractor>
@Inject internal constructor(interactor: I): BasePresenter<V, I>(interactor = interactor),
    GrammarReviseWritingPresenter<V, I> {
    override fun start() {
        interactor?.let {
            var coroutine = GlobalScope.launch {
                var card = it.getGrammarRevisionFlashcard(2)
                var grammar = it.getGrammar(card.grammarId)
                getView()?.setContent(card, grammar)
            }
            while (!coroutine.isCompleted){}
            if(coroutine.isCompleted){
                getView()?.clickSoundButton()
            }
        }
    }

}