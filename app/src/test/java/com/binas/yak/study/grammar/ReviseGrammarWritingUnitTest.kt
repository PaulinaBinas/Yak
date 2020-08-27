package com.binas.yak.study.grammar

import com.binas.yak.data.model.RevisionType
import com.binas.yak.data.model.grammar.Grammar
import com.binas.yak.data.model.grammar.GrammarRevisionFlashcard
import com.binas.yak.data.preferences.PreferenceHelperImpl
import com.binas.yak.ui.study.common.compareWriting.interactor.CompareWritingInteractor
import com.binas.yak.ui.study.common.compareWriting.interactor.CompareWritingInteractorImpl
import com.binas.yak.ui.study.common.compareWriting.presenter.CompareWritingPresenterImpl
import com.binas.yak.ui.study.common.compareWriting.view.CompareWritingActivity
import com.binas.yak.ui.study.common.compareWriting.view.CompareWritingView
import com.binas.yak.ui.study.grammar.reviseWriting.interactor.GrammarReviseWritingInteractor
import com.binas.yak.ui.study.grammar.reviseWriting.interactor.GrammarReviseWritingInteractorImpl
import com.binas.yak.ui.study.grammar.reviseWriting.presenter.GrammarReviseWritingPresenterImpl
import com.binas.yak.ui.study.grammar.reviseWriting.view.GrammarReviseWritingActivity
import com.binas.yak.ui.study.grammar.reviseWriting.view.GrammarReviseWritingView
import com.binas.yak.util.impl.DailyFlashcardQueueImpl
import com.binas.yak.util.impl.SpacedRepetitionSchedulerImpl
import org.junit.Test
import org.mockito.Mockito.*
import java.time.LocalDate

class ReviseGrammarWritingUnitTest {

    var reviseInteractor = mock(GrammarReviseWritingInteractorImpl::class.java)
    var reviseView = mock(GrammarReviseWritingActivity::class.java)
    var checkInteractor = mock(CompareWritingInteractorImpl::class.java)
    var checkView = mock(CompareWritingActivity::class.java)
    var preferenceHelper = mock(PreferenceHelperImpl::class.java)
    var grammar = Grammar(2L, "ང་མི་", "ཡིན", "།", "am1", 5L)
    var grammarRevisionFlashcard = GrammarRevisionFlashcard(1L, 2L, RevisionType.WRITING)

    @Test
    fun passesDataFromInteractorToView() {
        /* arrange */
        var presenter = GrammarReviseWritingPresenterImpl<GrammarReviseWritingView, GrammarReviseWritingInteractor>(reviseInteractor)
        `when`(reviseInteractor.getGrammar(2L)).thenReturn(grammar)
        `when`(reviseInteractor.getGrammarRevisionFlashcard(1L)).thenReturn(grammarRevisionFlashcard)

        /* act */
        presenter.onAttach(reviseView)
        presenter.start(1L)

        /* assert */
        verify(reviseView).setContent(grammarRevisionFlashcard, grammar)
    }

    @Test
    fun schedulesCardCorrectlyOnRemembered() {
        /* arrange */
        grammarRevisionFlashcard.interval = 8
        grammarRevisionFlashcard.nextDisplayTime = LocalDate.now()
        var queue = DailyFlashcardQueueImpl()
        var scheduler = SpacedRepetitionSchedulerImpl()
        var presenter = CompareWritingPresenterImpl<CompareWritingView, CompareWritingInteractor>(checkInteractor, queue, scheduler, preferenceHelper)
        `when`(checkInteractor.getCard(1L, "grammar")).thenReturn(grammarRevisionFlashcard)

        /* act */
        presenter.onAttach(checkView)
        presenter.reviseCard("grammar", 1L, true)

        /* assert */
        verify(checkInteractor).saveCard(grammarRevisionFlashcard, "grammar")
        assert(queue.isQueueEmpty())
        assert(grammarRevisionFlashcard.interval == 13L)
        assert(grammarRevisionFlashcard.nextDisplayTime == LocalDate.now().plusDays(13))
    }

    @Test
    fun resetsCardOnForgotten() {
        /* arrange */
        grammarRevisionFlashcard.interval = 8
        grammarRevisionFlashcard.nextDisplayTime = LocalDate.now()
        var queue = DailyFlashcardQueueImpl()
        var scheduler = SpacedRepetitionSchedulerImpl()
        var presenter = CompareWritingPresenterImpl<CompareWritingView, CompareWritingInteractor>(checkInteractor, queue, scheduler, preferenceHelper)
        `when`(checkInteractor.getCard(1L, "grammar")).thenReturn(grammarRevisionFlashcard)

        /* act */
        presenter.onAttach(checkView)
        presenter.reviseCard("grammar", 1L, false)

        /* assert */
        verify(checkInteractor).saveCard(grammarRevisionFlashcard, "grammar")
        assert(!queue.isQueueEmpty())
        assert(queue.todaysFlashcards.contains(grammarRevisionFlashcard))
        assert(grammarRevisionFlashcard.interval == 0L)
        assert(grammarRevisionFlashcard.nextDisplayTime == LocalDate.now())
    }
}