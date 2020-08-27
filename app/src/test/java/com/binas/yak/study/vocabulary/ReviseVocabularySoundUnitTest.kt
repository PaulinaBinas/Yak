package com.binas.yak.study.vocabulary

import com.binas.yak.data.model.RevisionType
import com.binas.yak.data.model.translation.Translation
import com.binas.yak.data.model.vocabulary.Vocabulary
import com.binas.yak.data.model.vocabulary.VocabularyRevisionFlashcard
import com.binas.yak.data.preferences.PreferenceHelperImpl
import com.binas.yak.ui.study.common.pronunciationCheck.interactor.PronunciationCheckInteractor
import com.binas.yak.ui.study.common.pronunciationCheck.interactor.PronunciationCheckInteractorImpl
import com.binas.yak.ui.study.common.pronunciationCheck.presenter.PronunciationCheckPresenterImpl
import com.binas.yak.ui.study.common.pronunciationCheck.view.PronunciationCheckActivity
import com.binas.yak.ui.study.common.pronunciationCheck.view.PronunciationCheckView
import com.binas.yak.ui.study.vocabulary.reviseSound.interactor.VocabularyReviseSoundInteractor
import com.binas.yak.ui.study.vocabulary.reviseSound.interactor.VocabularyReviseSoundInteractorImpl
import com.binas.yak.ui.study.vocabulary.reviseSound.presenter.VocabularyReviseSoundPresenterImpl
import com.binas.yak.ui.study.vocabulary.reviseSound.view.VocabularyReviseSoundActivity
import com.binas.yak.ui.study.vocabulary.reviseSound.view.VocabularyReviseSoundView
import com.binas.yak.util.impl.DailyFlashcardQueueImpl
import com.binas.yak.util.impl.SpacedRepetitionSchedulerImpl
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.*
import java.time.LocalDate

class ReviseVocabularySoundUnitTest {

    var flashcardInteractor = mock(VocabularyReviseSoundInteractorImpl::class.java)
    var flashcardView = mock(VocabularyReviseSoundActivity::class.java)
    var checkInteractor = mock(PronunciationCheckInteractorImpl::class.java)
    var checkView = mock(PronunciationCheckActivity::class.java)
    var preferenceHelper = Mockito.mock(PreferenceHelperImpl::class.java)
    var vocabulary = Vocabulary(2L, "ང་", "ime", 281L)
    var revisionFlashcard = VocabularyRevisionFlashcard(4L, 2L, RevisionType.PRONUNCIATION)
    var translation = Translation(281L, "Ja", "I")

    @Test
    fun passesDataFromInteractorToViewCorrectly() {
        /* arrange */
        var presenter = VocabularyReviseSoundPresenterImpl<VocabularyReviseSoundView, VocabularyReviseSoundInteractor>(flashcardInteractor)
        `when`(flashcardInteractor.getVocabulary(2L)).thenReturn(vocabulary)
        `when`(flashcardInteractor.getVocabularyRevisionFlashcard(4L)).thenReturn(revisionFlashcard)

        /* act */
        presenter.onAttach(flashcardView)
        presenter.start(4L)

        /* assert */
        verify(flashcardView).setContent(revisionFlashcard, vocabulary)
    }

    @Test
    fun schedulesCardCorrectlyOnRemembered() {
        /* arrange */
        revisionFlashcard.nextDisplayTime = LocalDate.now()
        revisionFlashcard.interval = 1
        var queue = DailyFlashcardQueueImpl()
        queue.addFlashcard(revisionFlashcard)
        var scheduler = SpacedRepetitionSchedulerImpl()
        var presenter = PronunciationCheckPresenterImpl<PronunciationCheckView, PronunciationCheckInteractor>(checkInteractor, queue, scheduler, preferenceHelper)
        `when`(checkInteractor.getCard(4L, "vocabulary")).thenReturn(revisionFlashcard)

        /* act */
        presenter.onAttach(checkView)
        presenter.reviseCard(4L, "vocabulary", true)

        /* assert */
        verify(checkInteractor).saveCard(revisionFlashcard, "vocabulary")
        assert(queue.isQueueEmpty())
        assert(revisionFlashcard.nextDisplayTime == LocalDate.now().plusDays(2))
        assert(revisionFlashcard.interval == 2L)
    }

    @Test
    fun schedulesCardCorrectlyOnForgotten() {
        /* arrange */
        revisionFlashcard.nextDisplayTime = LocalDate.now()
        revisionFlashcard.interval = 1
        var queue = DailyFlashcardQueueImpl()
        queue.addFlashcard(revisionFlashcard)
        var scheduler = SpacedRepetitionSchedulerImpl()
        var presenter = PronunciationCheckPresenterImpl<PronunciationCheckView, PronunciationCheckInteractor>(checkInteractor, queue, scheduler, preferenceHelper)
        `when`(checkInteractor.getCard(4L, "vocabulary")).thenReturn(revisionFlashcard)

        /* act */
        presenter.onAttach(checkView)
        presenter.reviseCard(4L, "vocabulary", false)

        /* assert */
        verify(checkInteractor).saveCard(revisionFlashcard, "vocabulary")
        assert(!queue.isQueueEmpty())
        assert(queue.todaysFlashcards.size == 1)
        assert(queue.todaysFlashcards.contains(revisionFlashcard))
        assert(revisionFlashcard.nextDisplayTime == LocalDate.now())
        assert(revisionFlashcard.interval == 0L)
    }
}