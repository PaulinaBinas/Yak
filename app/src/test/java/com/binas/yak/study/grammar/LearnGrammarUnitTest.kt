package com.binas.yak.study.grammar

import com.binas.yak.data.model.RevisionType
import com.binas.yak.data.model.grammar.Grammar
import com.binas.yak.data.model.grammar.GrammarRevisionFlashcard
import com.binas.yak.data.model.grammar.GrammarStudyFlashcard
import com.binas.yak.data.model.translation.Translation
import com.binas.yak.data.preferences.PreferenceHelperImpl
import com.binas.yak.ui.study.grammar.learn.learnWriting.interactor.LearnGrammarWritingInteractor
import com.binas.yak.ui.study.grammar.learn.learnWriting.interactor.LearnGrammarWritingInteractorImpl
import com.binas.yak.ui.study.grammar.learn.learnWriting.presenter.LearnGrammarWritingPresenterImpl
import com.binas.yak.ui.study.grammar.learn.learnWriting.view.LearnGrammarWritingActivity
import com.binas.yak.ui.study.grammar.learn.learnWriting.view.LearnGrammarWritingView
import com.binas.yak.ui.study.grammar.learn.studyCard.interactor.GrammarStudyCardInteractor
import com.binas.yak.ui.study.grammar.learn.studyCard.interactor.GrammarStudyCardInteractorImpl
import com.binas.yak.ui.study.grammar.learn.studyCard.presenter.GrammarStudyCardPresenterImpl
import com.binas.yak.ui.study.grammar.learn.studyCard.view.GrammarStudyCardActivity
import com.binas.yak.ui.study.grammar.learn.studyCard.view.GrammarStudyCardView
import com.binas.yak.util.impl.DailyFlashcardQueueImpl
import org.junit.Test
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.Mockito.*

class LearnGrammarUnitTest {

    var flashcardView = mock(GrammarStudyCardActivity::class.java)
    var flashcardInteractor = mock(GrammarStudyCardInteractorImpl::class.java)
    var learnInteractor = mock(LearnGrammarWritingInteractorImpl::class.java)
    var learnView = mock(LearnGrammarWritingActivity::class.java)
    var preferenceHelper = mock(PreferenceHelperImpl::class.java)
    var card = GrammarStudyFlashcard(2L, 2L)
    var grammar = Grammar(2L, "ང་མི་", "ཡིན", "།", "am1", 5L)
    var grammarRevisionFlashcard = GrammarRevisionFlashcard(1L, 2L, RevisionType.WRITING)
    var translation = Translation(5L, "Jestem człowiekiem.", "I'm a human.")

    @Test
    fun passesDataFromInteractorToView() {
        /* arrange */
        var flashcardPresenter = GrammarStudyCardPresenterImpl<GrammarStudyCardView, GrammarStudyCardInteractor>(flashcardInteractor)
        `when`(flashcardInteractor.getGrammar(anyLong())).thenReturn(grammar)
        `when`(flashcardInteractor.getTranslation(anyLong())).thenReturn(translation)
        `when`(flashcardInteractor.getGrammarStudyCard(anyLong())).thenReturn(card)

        /* act */
        flashcardPresenter.onAttach(flashcardView)
        flashcardPresenter.start(2L)

        /* assert */
        verify(flashcardView).setContent(card, grammar, translation)
    }

    @Test
    fun updatesFlashcardStatusAfterStudy() {
        /* arrange */
        var queue = DailyFlashcardQueueImpl()
        queue.addFlashcard(card)
        var presenter = LearnGrammarWritingPresenterImpl<LearnGrammarWritingView, LearnGrammarWritingInteractor>(learnInteractor, queue, preferenceHelper)
        `when`(learnInteractor.getAllMatchingRevisionFlashcards(anyLong())).thenReturn(listOf(grammarRevisionFlashcard))

        /* act */
        presenter.onAttach(learnView)
        presenter?.markCardAsStudied(2L)
        presenter?.scheduleReviewCards(2L)

        /* assert */
        verify(learnInteractor).markCardAsStudied(2L)
        assert(!queue.isQueueEmpty())
        assert(queue.todaysFlashcards.size == 1)
        assert(queue.todaysFlashcards.contains(grammarRevisionFlashcard))
    }
}