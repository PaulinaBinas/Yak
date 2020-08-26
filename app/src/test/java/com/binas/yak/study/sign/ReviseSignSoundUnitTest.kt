package com.binas.yak.study.sign

import com.binas.yak.data.model.RevisionType
import com.binas.yak.data.model.sign.Sign
import com.binas.yak.data.model.sign.SignRevisionFlashcard
import com.binas.yak.data.preferences.PreferenceHelperImpl
import com.binas.yak.ui.study.common.pronunciationCheck.interactor.PronunciationCheckInteractor
import com.binas.yak.ui.study.common.pronunciationCheck.interactor.PronunciationCheckInteractorImpl
import com.binas.yak.ui.study.common.pronunciationCheck.presenter.PronunciationCheckPresenterImpl
import com.binas.yak.ui.study.common.pronunciationCheck.view.PronunciationCheckActivity
import com.binas.yak.ui.study.common.pronunciationCheck.view.PronunciationCheckView
import com.binas.yak.ui.study.sign.reviseSound.interactor.SignReviseSoundInteractor
import com.binas.yak.ui.study.sign.reviseSound.interactor.SignReviseSoundInteractorImpl
import com.binas.yak.ui.study.sign.reviseSound.presenter.SignReviseSoundPresenterImpl
import com.binas.yak.ui.study.sign.reviseSound.view.SignReviseSoundActivity
import com.binas.yak.ui.study.sign.reviseSound.view.SignReviseSoundView
import com.binas.yak.util.impl.DailyFlashcardQueueImpl
import com.binas.yak.util.impl.SpacedRepetitionSchedulerImpl
import org.junit.Test
import org.mockito.Mockito.*
import java.time.LocalDate

class ReviseSignSoundUnitTest {

    var flashcardInteractor = mock(SignReviseSoundInteractorImpl::class.java)
    var flashcardView = mock(SignReviseSoundActivity::class.java)
    var checkInteractor = mock(PronunciationCheckInteractorImpl::class.java)
    var checkView = mock(PronunciationCheckActivity::class.java)
    var preferenceHelper = mock(PreferenceHelperImpl::class.java)
    var sign = Sign(1L, "ཀ", "ka", 1L)
    var revisionFlashcard = SignRevisionFlashcard(3L, RevisionType.PRONUNCIATION, 1L)

    @Test
    fun passesDataFromInteractorToViewCorrectly() {
        /* arrange */
        var presenter = SignReviseSoundPresenterImpl<SignReviseSoundView, SignReviseSoundInteractor>(flashcardInteractor)
        `when`(flashcardInteractor.getSign(1L)).thenReturn(sign)
        `when`(flashcardInteractor.getSignRevisionCard(3L)).thenReturn(revisionFlashcard)

        /* act */
        presenter.onAttach(flashcardView)
        presenter.start(3L)

        /* assert */
        verify(flashcardView).setContent(revisionFlashcard, sign)
    }

    @Test
    fun schedulesCardCorrectlyOnRemembered() {
        /* arrange */
        revisionFlashcard.nextDisplayTime = LocalDate.now()
        revisionFlashcard.interval = 5
        var queue = DailyFlashcardQueueImpl()
        queue.addFlashcard(revisionFlashcard)
        var scheduler = SpacedRepetitionSchedulerImpl()
        var presenter = PronunciationCheckPresenterImpl<PronunciationCheckView, PronunciationCheckInteractor>(checkInteractor, queue, scheduler, preferenceHelper)
        `when`(checkInteractor.getCard(3L, "sign")).thenReturn(revisionFlashcard)

        /* act */
        presenter.onAttach(checkView)
        presenter.reviseCard(3L, "sign", true)

        /* assert */
        verify(checkInteractor).saveCard(revisionFlashcard, "sign")
        assert(queue.isQueueEmpty())
        assert(revisionFlashcard.nextDisplayTime == LocalDate.now().plusDays(8))
        assert(revisionFlashcard.interval == 8L)
    }

    @Test
    fun schedulesCardCorrectlyOnForgotten() {
        /* arrange */
        revisionFlashcard.nextDisplayTime = LocalDate.now()
        revisionFlashcard.interval = 5
        var queue = DailyFlashcardQueueImpl()
        queue.addFlashcard(revisionFlashcard)
        var scheduler = SpacedRepetitionSchedulerImpl()
        var presenter = PronunciationCheckPresenterImpl<PronunciationCheckView, PronunciationCheckInteractor>(checkInteractor, queue, scheduler, preferenceHelper)
        `when`(checkInteractor.getCard(3L, "sign")).thenReturn(revisionFlashcard)

        /* act */
        presenter.onAttach(checkView)
        presenter.reviseCard(3L, "sign", false)

        /* assert */
        verify(checkInteractor).saveCard(revisionFlashcard, "sign")
        assert(!queue.isQueueEmpty())
        assert(queue.todaysFlashcards.size == 1)
        assert(queue.todaysFlashcards.contains(revisionFlashcard))
        assert(revisionFlashcard.nextDisplayTime == LocalDate.now())
        assert(revisionFlashcard.interval == 0L)
    }
}