package com.binas.yak.ui.study.presenter

import com.binas.yak.data.model.Flashcard
import com.binas.yak.data.model.RevisionType
import com.binas.yak.data.model.grammar.GrammarRevisionFlashcard
import com.binas.yak.data.model.grammar.GrammarStudyFlashcard
import com.binas.yak.data.model.sign.SignRevisionFlashcard
import com.binas.yak.data.model.sign.SignStudyFlashcard
import com.binas.yak.data.model.studyOrder.StudyOrder
import com.binas.yak.data.model.vocabulary.VocabularyRevisionFlashcard
import com.binas.yak.data.model.vocabulary.VocabularyStudyFlashcard
import com.binas.yak.data.preferences.PreferenceHelper
import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.study.interactor.StudyInteractor
import com.binas.yak.ui.study.view.StudyView
import com.binas.yak.util.DailyFlashcardQueue
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class StudyPresenterImpl<V: StudyView, I: StudyInteractor> @Inject internal constructor(var preferenceHelper: PreferenceHelper, interactor: I, var queue: DailyFlashcardQueue): BasePresenter<V, I>(interactor = interactor), StudyPresenter<V, I> {

    override fun start() {
        interactor?.let { it ->
            var studyOrder: List<StudyOrder> = ArrayList()
            var newDailyItems = 0
            var dailyLimit = preferenceHelper.getDailyCardLimit()
            lateinit var nextItem: Flashcard

            if(queue.isQueueEmpty()) {
                var coroutine = GlobalScope.launch {
                    studyOrder = it.getStudyOrderList()
                }
                while (!coroutine.isCompleted){}
                for(item in studyOrder) {
                    if (newDailyItems < dailyLimit) {
                        addStudyCardToQueue(item, it)
                        newDailyItems++
                    }
                }
                addRevisionCardsToQueue(it)
            }

            queue.getNextFlashcard().let { nextItem = it!! }
            if(nextItem != null) {
                goToNextView(nextItem)
            } else {
                getView()?.displayStudyOver()
            }
        }
    }

    private fun addStudyCardToQueue(item: StudyOrder, it: StudyInteractor) {
        var coroutine = GlobalScope.launch {
            if (item.signStudyId != null) {
                var card = it.getSignStudyCard(item.signStudyId!!)
                if(card.ifStudied == 0L) {
                    queue.addFlashcard(card)
                }
            } else if (item.vocabularyStudyId != null) {
                var card = it.getVocabularyStudyFlashcard(item.vocabularyStudyId!!)
                if(card.ifStudied == 0L) {
                    queue.addFlashcard(card)
                }
            } else if (item.grammarStudyId != null) {
                var card = it.getGrammarStudyFlashcard(item.grammarStudyId!!)
                if(card.ifStudied == 0L) {
                    queue.addFlashcard(card)
                }
            }
        }
        while (!coroutine.isCompleted) {}
    }

    private fun addRevisionCardsToQueue(interactor: StudyInteractor) {
        var cards: List<Flashcard> = ArrayList()
        var coroutine = GlobalScope.launch {
            var signs = interactor.getScheduledSignRevisionFlashcards()
            var vocabulary = interactor.getScheduledVocabularyRevisionFlashcards()
            var grammar = interactor.getScheduledGrammarRevisionFlashcards()
            for(item in signs) { cards += item }
            for(item in vocabulary) { cards += item }
            for(item in grammar) { cards += item }
            cards = cards.shuffled()
        }
        while(!coroutine.isCompleted) {}
        if(coroutine.isCompleted){
            cards = cards.shuffled()
            for(item in cards) {
                queue.addFlashcard(item)
            }
        }
    }

    private fun goToNextView(nextItem: Flashcard) {
        when(nextItem){
            is SignStudyFlashcard -> getView()?.onClickGoToStudySign((nextItem as SignStudyFlashcard).id)
            is VocabularyStudyFlashcard -> getView()?.onClickGoToStudyVocab((nextItem as VocabularyStudyFlashcard).id)
            is GrammarStudyFlashcard -> getView()?.onClickGoToStudyGrammar((nextItem as GrammarStudyFlashcard).id)
            is SignRevisionFlashcard -> {
                when((nextItem as SignRevisionFlashcard).revisionType) {
                    RevisionType.WRITING -> getView()?.onClickGoToSignReviseWriting((nextItem as SignRevisionFlashcard).id)
                    RevisionType.MEANING -> getView()?.onClickGoToSignReviseDecision((nextItem as SignRevisionFlashcard).id)
                    RevisionType.PRONUNCIATION -> getView()?.onClickGoToSignReviseSound((nextItem as SignRevisionFlashcard).id)
                    else -> {}
                }
            }
            is VocabularyRevisionFlashcard -> {
                when((nextItem as VocabularyRevisionFlashcard).revisionType) {
                    RevisionType.WRITING -> getView()?.onClickGoToVocabReviseWriting((nextItem as VocabularyRevisionFlashcard).id)
                    RevisionType.MEANING -> getView()?.onClickGoToVocabReviseMeaning((nextItem as VocabularyRevisionFlashcard).id)
                    RevisionType.PRONUNCIATION -> getView()?.onClickGoToVocabReviseSound((nextItem as VocabularyRevisionFlashcard).id)
                    else -> {}
                }
            }
            is GrammarRevisionFlashcard -> {
                when((nextItem as GrammarRevisionFlashcard).revisionType) {
                    RevisionType.WRITING -> getView()?.onClickGoToGrammarReviseWriting((nextItem as GrammarRevisionFlashcard).id)
                    RevisionType.PRONUNCIATION -> getView()?.onClickGoToGrammarReviseWriting((nextItem as GrammarRevisionFlashcard).id)
                    else -> {}
                }
            }
            else -> {}
        }
    }
}