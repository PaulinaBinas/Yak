package com.binas.yak.ui.study.grammar.learn.studyCard.presenter

import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.study.grammar.learn.studyCard.interactor.GrammarStudyCardInteractor
import com.binas.yak.ui.study.grammar.learn.studyCard.view.GrammarStudyCardView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class GrammarStudyCardPresenterImpl<V: GrammarStudyCardView, I: GrammarStudyCardInteractor>
@Inject internal constructor(interactor: I): BasePresenter<V, I>(interactor = interactor), GrammarStudyCardPresenter<V, I> {

    override fun start(id: Long) {
        interactor?.let {
            var coroutine = GlobalScope.launch {
                var card = it.getGrammarStudyCard(id)
                var grammar = it.getGrammar(card.grammarId)
                var translation = grammar.translationId?.let { it1 -> it.getTranslation(it1) }
                getView()?.setContent(card, grammar, translation)
            }
            while (!coroutine.isCompleted) {

            }
            if(coroutine.isCompleted) {
                getView()?.clickPlaySound()
            }
        }
    }
}