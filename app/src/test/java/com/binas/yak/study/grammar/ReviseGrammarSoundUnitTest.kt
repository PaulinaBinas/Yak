package com.binas.yak.study.grammar

import com.binas.yak.data.model.RevisionType
import com.binas.yak.data.model.grammar.Grammar
import com.binas.yak.data.model.grammar.GrammarRevisionFlashcard
import com.binas.yak.data.model.translation.Translation
import com.binas.yak.data.preferences.PreferenceHelperImpl
import com.binas.yak.ui.study.grammar.reviseSound.interactor.GrammarReviseSoundInteractor
import com.binas.yak.ui.study.grammar.reviseSound.interactor.GrammarReviseSoundInteractorImpl
import com.binas.yak.ui.study.grammar.reviseSound.presenter.GrammarReviseSoundPresenterImpl
import com.binas.yak.ui.study.grammar.reviseSound.pronunciationCheck.interactor.GrammarPronunciationCheckInteractor
import com.binas.yak.ui.study.grammar.reviseSound.pronunciationCheck.interactor.GrammarPronunciationCheckInteractorImpl
import com.binas.yak.ui.study.grammar.reviseSound.pronunciationCheck.presenter.GrammarPronunciationCheckPresenterImpl
import com.binas.yak.ui.study.grammar.reviseSound.pronunciationCheck.view.GrammarPronunciationCheckActivity
import com.binas.yak.ui.study.grammar.reviseSound.pronunciationCheck.view.GrammarPronunciationCheckView
import com.binas.yak.ui.study.grammar.reviseSound.view.GrammarReviseSoundActivity
import com.binas.yak.ui.study.grammar.reviseSound.view.GrammarReviseSoundView
import com.binas.yak.util.impl.DailyFlashcardQueueImpl
import com.binas.yak.util.impl.SpacedRepetitionSchedulerImpl
import org.junit.Test
import org.mockito.Mockito.*
import java.time.LocalDate

class ReviseGrammarSoundUnitTest {

    var reviseInteractor = mock(GrammarReviseSoundInteractorImpl::class.java)
    var reviseView = mock(GrammarReviseSoundActivity::class.java)
    var checkInteractor = mock(GrammarPronunciationCheckInteractorImpl::class.java)
    var checkView = mock(GrammarPronunciationCheckActivity::class.java)
    var preferenceHelper = mock(PreferenceHelperImpl::class.java)
    var grammar = Grammar(2L, "ང་མི་", "ཡིན", "།", "am1", 5L)
    var grammarRevisionFlashcard = GrammarRevisionFlashcard(1L, 2L, RevisionType.PRONUNCIATION)
    var translation = Translation(5L, "Jestem człowiekiem.", "I'm a human.")

    @Test
    fun passesDataFromInteractorToView() {
        /* arrange */
        var presenter = GrammarReviseSoundPresenterImpl<GrammarReviseSoundView, GrammarReviseSoundInteractor>(reviseInteractor)
        `when`(reviseInteractor.getGrammar(2L)).thenReturn(grammar)
        `when`(reviseInteractor.getGrammarRevisionFlashcard(1L)).thenReturn(grammarRevisionFlashcard)
        `when`(reviseInteractor.getTranslation(5L)).thenReturn(translation)

        /* act */
        presenter.onAttach(reviseView)
        presenter.start(1L)

        /* assert */
        verify(reviseView).setContent(grammarRevisionFlashcard, grammar, translation)
    }

    @Test
    fun schedulesCardCorrectlyOnRemembered() {
        /* arrange */
        grammarRevisionFlashcard.interval = 8
        grammarRevisionFlashcard.nextDisplayTime = LocalDate.now()
        var queue = DailyFlashcardQueueImpl()
        queue.addFlashcard(grammarRevisionFlashcard)
        var spacedRepetitionScheduler = SpacedRepetitionSchedulerImpl()
        var presenter = GrammarPronunciationCheckPresenterImpl<GrammarPronunciationCheckView, GrammarPronunciationCheckInteractor>(checkInteractor, queue, spacedRepetitionScheduler, preferenceHelper)
        `when`(checkInteractor.getCard(1L)).thenReturn(grammarRevisionFlashcard)
        `when`(checkInteractor.getUserStudyTime(1L)).thenReturn(10.0)

        /* act */
        presenter.onAttach(checkView)
        presenter.reviseCard(true, 1L)

        /* assert */
        verify(checkInteractor).saveCard(grammarRevisionFlashcard)
        assert(grammarRevisionFlashcard.interval == 13L)
        assert(grammarRevisionFlashcard.nextDisplayTime == LocalDate.now().plusDays(13))
        assert(queue.isQueueEmpty())
    }

    @Test
    fun resetsCardOnForgotten() {
        /* arrange */
        grammarRevisionFlashcard.interval = 8
        grammarRevisionFlashcard.nextDisplayTime = LocalDate.now()
        var queue = DailyFlashcardQueueImpl()
        queue.addFlashcard(grammarRevisionFlashcard)
        var spacedRepetitionScheduler = SpacedRepetitionSchedulerImpl()
        var presenter = GrammarPronunciationCheckPresenterImpl<GrammarPronunciationCheckView, GrammarPronunciationCheckInteractor>(checkInteractor, queue, spacedRepetitionScheduler, preferenceHelper)
        `when`(checkInteractor.getCard(1L)).thenReturn(grammarRevisionFlashcard)
        `when`(checkInteractor.getUserStudyTime(1L)).thenReturn(10.0)

        /* act */
        presenter.onAttach(checkView)
        presenter.reviseCard(false, 1L)

        /* assert */
        verify(checkInteractor).saveCard(grammarRevisionFlashcard)
        assert(grammarRevisionFlashcard.interval == 0L)
        assert(grammarRevisionFlashcard.nextDisplayTime == LocalDate.now())
        assert(!queue.isQueueEmpty())
        assert(queue.todaysFlashcards.contains(grammarRevisionFlashcard))
    }
}