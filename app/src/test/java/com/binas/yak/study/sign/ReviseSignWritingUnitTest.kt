package com.binas.yak.study.sign

import com.binas.yak.data.model.RevisionType
import com.binas.yak.data.model.sign.Sign
import com.binas.yak.data.model.sign.SignRevisionFlashcard
import com.binas.yak.data.preferences.PreferenceHelperImpl
import com.binas.yak.ui.study.common.compareWriting.interactor.CompareWritingInteractor
import com.binas.yak.ui.study.common.compareWriting.interactor.CompareWritingInteractorImpl
import com.binas.yak.ui.study.common.compareWriting.presenter.CompareWritingPresenterImpl
import com.binas.yak.ui.study.common.compareWriting.view.CompareWritingActivity
import com.binas.yak.ui.study.common.compareWriting.view.CompareWritingView
import com.binas.yak.ui.study.sign.reviseWriting.interactor.SignReviseWritingInteractor
import com.binas.yak.ui.study.sign.reviseWriting.interactor.SignReviseWritingInteractorImpl
import com.binas.yak.ui.study.sign.reviseWriting.presenter.SignReviseWritingPresenterImpl
import com.binas.yak.ui.study.sign.reviseWriting.view.SignReviseWritingActivity
import com.binas.yak.ui.study.sign.reviseWriting.view.SignReviseWritingView
import com.binas.yak.util.impl.DailyFlashcardQueueImpl
import com.binas.yak.util.impl.SpacedRepetitionSchedulerImpl
import org.junit.Test
import org.mockito.Mockito.*
import java.time.LocalDate

class ReviseSignWritingUnitTest {

    var flashcardInteractor = mock(SignReviseWritingInteractorImpl::class.java)
    var flashcardView = mock(SignReviseWritingActivity::class.java)
    var checkInteractor = mock(CompareWritingInteractorImpl::class.java)
    var checkView = mock(CompareWritingActivity::class.java)
    var preferenceHelper = mock(PreferenceHelperImpl::class.java)
    var sign = Sign(1L, "ཀ", "ka", 1L)
    var revisionFlashcard = SignRevisionFlashcard(3L, RevisionType.PRONUNCIATION, 1L)

    @Test
    fun passesDataFromInteractorToViewCorrectly() {
        /* arrange */
        var presenter = SignReviseWritingPresenterImpl<SignReviseWritingView, SignReviseWritingInteractor>(flashcardInteractor)
        `when`(flashcardInteractor.getSign(1L)).thenReturn(sign)
        `when`(flashcardInteractor.getSignRevisionFlashcard(3L)).thenReturn(revisionFlashcard)

        /* act */
        presenter.onAttach(flashcardView)
        presenter.start(3L)

        /* assert */
        verify(flashcardView).setContent(revisionFlashcard, sign)
    }

    @Test
    fun schedulesCardCorrectlyAfterRemembered() {
        /* arrange */
        revisionFlashcard.nextDisplayTime = LocalDate.now()
        revisionFlashcard.interval = 3
        var queue = DailyFlashcardQueueImpl()
        queue.addFlashcard(revisionFlashcard)
        var scheduler = SpacedRepetitionSchedulerImpl()
        var presenter = CompareWritingPresenterImpl<CompareWritingView, CompareWritingInteractor>(checkInteractor, queue, scheduler, preferenceHelper)
        `when`(checkInteractor.getCard(3L, "sign")).thenReturn(revisionFlashcard)

        /* act */
        presenter.onAttach(checkView)
        presenter.reviseCard("sign", 3L, true)

        /* assert */
        verify(checkInteractor).saveCard(revisionFlashcard, "sign")
        assert(queue.isQueueEmpty())
        assert(revisionFlashcard.nextDisplayTime == LocalDate.now().plusDays(5))
        assert(revisionFlashcard.interval == 5L)
    }

    @Test
    fun schedulesCardCorrectlyAfterForgotten() {
        /* arrange */
        revisionFlashcard.nextDisplayTime = LocalDate.now()
        revisionFlashcard.interval = 3
        var queue = DailyFlashcardQueueImpl()
        queue.addFlashcard(revisionFlashcard)
        var scheduler = SpacedRepetitionSchedulerImpl()
        var presenter = CompareWritingPresenterImpl<CompareWritingView, CompareWritingInteractor>(checkInteractor, queue, scheduler, preferenceHelper)
        `when`(checkInteractor.getCard(3L, "sign")).thenReturn(revisionFlashcard)

        /* act */
        presenter.onAttach(checkView)
        presenter.reviseCard("sign", 3L, false)

        /* assert */
        verify(checkInteractor).saveCard(revisionFlashcard, "sign")
        assert(!queue.isQueueEmpty())
        assert(queue.todaysFlashcards.size == 1)
        assert(queue.todaysFlashcards.contains(revisionFlashcard))
        assert(revisionFlashcard.nextDisplayTime == LocalDate.now())
        assert(revisionFlashcard.interval == 0L)
    }
}