package com.binas.yak.study.vocabulary

import com.binas.yak.data.model.RevisionType
import com.binas.yak.data.model.translation.Translation
import com.binas.yak.data.model.vocabulary.Vocabulary
import com.binas.yak.data.model.vocabulary.VocabularyRevisionFlashcard
import com.binas.yak.data.preferences.PreferenceHelperImpl
import com.binas.yak.ui.study.common.compareWriting.interactor.CompareWritingInteractor
import com.binas.yak.ui.study.common.compareWriting.interactor.CompareWritingInteractorImpl
import com.binas.yak.ui.study.common.compareWriting.presenter.CompareWritingPresenterImpl
import com.binas.yak.ui.study.common.compareWriting.view.CompareWritingActivity
import com.binas.yak.ui.study.common.compareWriting.view.CompareWritingView
import com.binas.yak.ui.study.vocabulary.reviseWriting.interactor.VocabularyReviseWritingInteractor
import com.binas.yak.ui.study.vocabulary.reviseWriting.interactor.VocabularyReviseWritinginteractorImpl
import com.binas.yak.ui.study.vocabulary.reviseWriting.presenter.VocabularyReviseWritingPresenterImpl
import com.binas.yak.ui.study.vocabulary.reviseWriting.view.VocabularyReviseWritingActivity
import com.binas.yak.ui.study.vocabulary.reviseWriting.view.VocabularyReviseWritingView
import com.binas.yak.util.impl.DailyFlashcardQueueImpl
import com.binas.yak.util.impl.SpacedRepetitionSchedulerImpl
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.*
import java.time.LocalDate

class ReviseVocabularyWritingUnitTest {

    var flashcardInteractor = mock(VocabularyReviseWritinginteractorImpl::class.java)
    var flashcardView = mock(VocabularyReviseWritingActivity::class.java)
    var checkInteractor = mock(CompareWritingInteractorImpl::class.java)
    var checkView = mock(CompareWritingActivity::class.java)
    var preferenceHelper = Mockito.mock(PreferenceHelperImpl::class.java)
    var vocabulary = Vocabulary(2L, "ང་", "ime", 281L)
    var revisionFlashcard = VocabularyRevisionFlashcard(4L, 2L, RevisionType.WRITING)
    var translation = Translation(281L, "Ja", "I")

    @Test
    fun passesDataFromInteractorToViewCorrectly() {
        /* arrange */
        var presenter = VocabularyReviseWritingPresenterImpl<VocabularyReviseWritingView, VocabularyReviseWritingInteractor>(flashcardInteractor)
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
        var presenter = CompareWritingPresenterImpl<CompareWritingView, CompareWritingInteractor>(checkInteractor, queue, scheduler, preferenceHelper)
        `when`(checkInteractor.getCard(4L, "vocabulary")).thenReturn(revisionFlashcard)

        /* act */
        presenter.onAttach(checkView)
        presenter.reviseCard("vocabulary", 4L, true)

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
        var presenter = CompareWritingPresenterImpl<CompareWritingView, CompareWritingInteractor>(checkInteractor, queue, scheduler, preferenceHelper)
        `when`(checkInteractor.getCard(4L, "vocabulary")).thenReturn(revisionFlashcard)

        /* act */
        presenter.onAttach(checkView)
        presenter.reviseCard("vocabulary", 4L, false)

        /* assert */
        verify(checkInteractor).saveCard(revisionFlashcard, "vocabulary")
        assert(!queue.isQueueEmpty())
        assert(queue.todaysFlashcards.size == 1)
        assert(queue.todaysFlashcards.contains(revisionFlashcard))
        assert(revisionFlashcard.nextDisplayTime == LocalDate.now())
        assert(revisionFlashcard.interval == 0L)
    }
}