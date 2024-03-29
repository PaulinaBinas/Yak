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
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class StudyPresenterImpl<V: StudyView, I: StudyInteractor> @Inject internal constructor(var preferenceHelper: PreferenceHelper, interactor: I, var queue: DailyFlashcardQueue): BasePresenter<V, I>(interactor = interactor), StudyPresenter<V, I> {

    private var newDailyItems = 0

    override fun start() {
        interactor?.let { it ->
            var studyOrder: List<StudyOrder> = ArrayList()
            var coroutine = GlobalScope.launch {
                var day = it.getStudyDay()
                newDailyItems = it.getStudyDay()?.elementsStudied ?: 0
            }
            while (!coroutine.isCompleted){}
            var dailyLimit = preferenceHelper.getDailyCardLimit()
            var nextItem: Flashcard? = null

            if(queue.isQueueEmpty()) {
                var coroutine = GlobalScope.launch {
                    studyOrder = it.getStudyOrderList()
                }
                while (!coroutine.isCompleted){}
                for(item in studyOrder) {
                    if (newDailyItems < dailyLimit) {
                        addStudyCardToQueue(item, it)
                    }
                }
                addRevisionCardsToQueue(it)
            } else if(newDailyItems >= dailyLimit) {
                var shuffledList = LinkedList<Flashcard>()
                queue.todaysFlashcards.shuffled().forEach { card -> shuffledList.add(card) }
                queue.todaysFlashcards = shuffledList
            }
            queue.getNextFlashcard()?.let { nextItem = it }
            nextItem?.let { goToNextView(it) } ?: getView()?.displayStudyOver()
        }
    }

    private fun addStudyCardToQueue(item: StudyOrder, it: StudyInteractor) {
        var coroutine = GlobalScope.launch {
            if (item.signStudyId != null) {
                var card = it.getSignStudyCard(item.signStudyId!!)
                if(card.ifStudied == 0L) {
                    queue.addFlashcard(card)
                    newDailyItems++
                }
            } else if (item.vocabularyStudyId != null) {
                var card = it.getVocabularyStudyFlashcard(item.vocabularyStudyId!!)
                if(card.ifStudied == 0L) {
                    queue.addFlashcard(card)
                    newDailyItems++
                }
            } else if (item.grammarStudyId != null) {
                var card = it.getGrammarStudyFlashcard(item.grammarStudyId!!)
                if(card.ifStudied == 0L) {
                    queue.addFlashcard(card)
                    newDailyItems++
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
                    RevisionType.PRONUNCIATION -> getView()?.onClickGoToGrammarReviseSound((nextItem as GrammarRevisionFlashcard).id)
                    else -> {}
                }
            }
            else -> {}
        }
    }
}