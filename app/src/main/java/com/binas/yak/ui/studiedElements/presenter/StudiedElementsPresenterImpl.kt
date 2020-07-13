package com.binas.yak.ui.studiedElements.presenter

import com.binas.yak.data.model.grammar.Grammar
import com.binas.yak.data.model.grammar.GrammarStudyFlashcard
import com.binas.yak.data.model.sign.Sign
import com.binas.yak.data.model.sign.SignStudyFlashcard
import com.binas.yak.data.model.vocabulary.Vocabulary
import com.binas.yak.data.model.vocabulary.VocabularyStudyFlashcard
import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.studiedElements.interactor.StudiedElementsInteractor
import com.binas.yak.ui.studiedElements.view.StudiedElementsView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class StudiedElementsPresenterImpl<V: StudiedElementsView, I: StudiedElementsInteractor>
@Inject internal constructor(interactor: I): BasePresenter<V, I>(interactor = interactor), StudiedElementsPresenter<V, I> {

    override fun start() {
        interactor?.let {
            var signCards: List<SignStudyFlashcard> = ArrayList()
            var vocabularyCards: List<VocabularyStudyFlashcard> = ArrayList()
            var grammarCards: List<GrammarStudyFlashcard> = ArrayList()
            var signs: MutableList<Sign> = ArrayList()
            var words: MutableList<Vocabulary> = ArrayList()
            var grammars: MutableList<Grammar> = ArrayList()
            var coroutine = GlobalScope.launch {
                signCards = it.getLearntSignCards()
                vocabularyCards = it.getLearntVocabularyCards()
                grammarCards = it.getLearntGrammarCards()
                for(signCard in signCards) {
                    signs.add(it.getSign(signCard.signId))
                }
                for(wordCard in vocabularyCards) {
                    words.add(it.getVocabulary(wordCard.vocabularyId))
                }
                for(grammarCard in grammarCards) {
                    grammars.add(it.getGrammar(grammarCard.grammarId))
                }
            }
            while(!coroutine.isCompleted){}
            if(coroutine.isCompleted){
                for(i in signs.indices) {
                    var card = signCards[i]
                    var sign = signs[i]
                    getView()?.addNewSignElement(card, sign)
                }
                for(i in words.indices) {
                    var card = vocabularyCards[i]
                    var vocabulary = words[i]
                    getView()?.addNewVocabularyElement(card, vocabulary)
                }
                for(i in grammars.indices) {
                    var card = grammarCards[i]
                    var grammar = grammars[i]
                    getView()?.addNewGrammarElement(card, grammar)
                }
            }
            if(signCards.isNotEmpty() || vocabularyCards.isNotEmpty() || grammarCards.isNotEmpty()) {
                getView()?.removeNoElementsTextView()
            }
        }
    }
}