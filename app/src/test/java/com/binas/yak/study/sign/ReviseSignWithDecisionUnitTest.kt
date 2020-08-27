package com.binas.yak.study.sign

import com.binas.yak.data.model.RevisionType
import com.binas.yak.data.model.sign.Sign
import com.binas.yak.data.model.sign.SignRevisionFlashcard
import com.binas.yak.data.preferences.PreferenceHelperImpl
import com.binas.yak.ui.study.sign.reviseWithDecision.interactor.SignReviseWithDecisionInteractor
import com.binas.yak.ui.study.sign.reviseWithDecision.interactor.SignReviseWithDecisionInteractorImpl
import com.binas.yak.ui.study.sign.reviseWithDecision.presenter.SignReviseWithDecisionPresenterImpl
import com.binas.yak.ui.study.sign.reviseWithDecision.view.SignReviseWithDecisionActivity
import com.binas.yak.util.impl.DailyFlashcardQueueImpl
import com.binas.yak.util.impl.SpacedRepetitionSchedulerImpl
import org.junit.Test
import org.mockito.Mockito.*
import java.time.LocalDate

class ReviseSignWithDecisionUnitTest {

    var interactor = mock(SignReviseWithDecisionInteractorImpl::class.java)
    var view = mock(SignReviseWithDecisionActivity::class.java)
    var preferenceHelper = mock(PreferenceHelperImpl::class.java)
    var sign = Sign(1L, "ཀ", "ka", 1L)
    var incorrectSign = Sign(2L, "ཁ", "kha", 2L)
    var revisionFlashcard = SignRevisionFlashcard(3L, RevisionType.MEANING, 1L)

    @Test
    fun flashcardDisplayedAndScheduledCorrectlyOnRemembered() {
        /* arrange */
        revisionFlashcard.nextDisplayTime = LocalDate.now()
        revisionFlashcard.interval = 21L
        revisionFlashcard.comparisonSignId = 2L
        var queue = DailyFlashcardQueueImpl()
        queue.addFlashcard(revisionFlashcard)
        var scheduler = SpacedRepetitionSchedulerImpl()
        var presenter = SignReviseWithDecisionPresenterImpl<SignReviseWithDecisionActivity, SignReviseWithDecisionInteractor>(interactor, scheduler, queue, preferenceHelper)
        `when`(interactor.getSign(1L)).thenReturn(sign)
        `when`(interactor.getSign(2L)).thenReturn(incorrectSign)
        `when`(interactor.getSignRevisionFlashcard(3L)).thenReturn(revisionFlashcard)

        /* act */
        presenter.onAttach(view)
        presenter.start(3L)
        presenter.reviseCard(3L, true)

        /* assert */
        verify(view).setContent(revisionFlashcard, sign, incorrectSign)
        verify(interactor).saveCard(revisionFlashcard)
        assert(queue.isQueueEmpty())
        assert(revisionFlashcard.interval == 34L)
        assert(revisionFlashcard.nextDisplayTime == LocalDate.now().plusDays(34))
    }

    @Test
    fun flashcardDisplayedAndScheduledCorrectlyOnForgotten() {
        /* arrange */
        revisionFlashcard.nextDisplayTime = LocalDate.now()
        revisionFlashcard.interval = 21
        revisionFlashcard.comparisonSignId = 2L
        var queue = DailyFlashcardQueueImpl()
        queue.addFlashcard(revisionFlashcard)
        var scheduler = SpacedRepetitionSchedulerImpl()
        var presenter = SignReviseWithDecisionPresenterImpl<SignReviseWithDecisionActivity, SignReviseWithDecisionInteractor>(interactor, scheduler, queue, preferenceHelper)
        `when`(interactor.getSign(1L)).thenReturn(sign)
        `when`(interactor.getSign(2L)).thenReturn(incorrectSign)
        `when`(interactor.getSignRevisionFlashcard(3L)).thenReturn(revisionFlashcard)

        /* act */
        presenter.onAttach(view)
        presenter.start(3L)
        presenter.reviseCard(3L, false)

        /* assert */
        verify(view).setContent(revisionFlashcard, sign, incorrectSign)
        verify(interactor).saveCard(revisionFlashcard)
        assert(!queue.isQueueEmpty())
        assert(queue.todaysFlashcards.contains(revisionFlashcard))
        assert(revisionFlashcard.interval == 0L)
        assert(revisionFlashcard.nextDisplayTime == LocalDate.now())
    }
}