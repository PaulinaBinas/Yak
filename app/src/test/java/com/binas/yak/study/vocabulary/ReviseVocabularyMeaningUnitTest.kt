package com.binas.yak.study.vocabulary

import com.binas.yak.data.model.RevisionType
import com.binas.yak.data.model.translation.Translation
import com.binas.yak.data.model.vocabulary.Vocabulary
import com.binas.yak.data.model.vocabulary.VocabularyRevisionFlashcard
import com.binas.yak.data.preferences.PreferenceHelperImpl
import com.binas.yak.ui.study.common.meaningCheck.interactor.MeaningCheckInteractor
import com.binas.yak.ui.study.common.meaningCheck.interactor.MeaningCheckInteractorImpl
import com.binas.yak.ui.study.common.meaningCheck.presenter.MeaningCheckPresenterImpl
import com.binas.yak.ui.study.common.meaningCheck.view.MeaningCheckActivity
import com.binas.yak.ui.study.common.meaningCheck.view.MeaningCheckView
import com.binas.yak.ui.study.vocabulary.reviseMeaning.interactor.VocabularyReviseMeaningInteractor
import com.binas.yak.ui.study.vocabulary.reviseMeaning.interactor.VocabularyReviseMeaningInteractorImpl
import com.binas.yak.ui.study.vocabulary.reviseMeaning.presenter.VocabularyReviseMeaningPresenterImpl
import com.binas.yak.ui.study.vocabulary.reviseMeaning.view.VocabularyReviseMeaningActivity
import com.binas.yak.ui.study.vocabulary.reviseMeaning.view.VocabularyReviseMeaningView
import com.binas.yak.util.impl.DailyFlashcardQueueImpl
import com.binas.yak.util.impl.SpacedRepetitionSchedulerImpl
import org.junit.Test
import org.mockito.Mockito.*
import java.time.LocalDate

class ReviseVocabularyMeaningUnitTest {

    var flashcardInteractor = mock(VocabularyReviseMeaningInteractorImpl::class.java)
    var flashcardView = mock(VocabularyReviseMeaningActivity::class.java)
    var checkInteractor = mock(MeaningCheckInteractorImpl::class.java)
    var checkView = mock(MeaningCheckActivity::class.java)
    var preferenceHelper = mock(PreferenceHelperImpl::class.java)
    var vocabulary = Vocabulary(2L, "ང་", "ime", 281L)
    var revisionFlashcard = VocabularyRevisionFlashcard(4L, 2L, RevisionType.MEANING)
    var translation = Translation(281L, "Ja", "I")

    @Test
    fun passesDataFromInteractorToViewCorrectly() {
        /* arrange */
        var presenter = VocabularyReviseMeaningPresenterImpl<VocabularyReviseMeaningView, VocabularyReviseMeaningInteractor>(flashcardInteractor)
        `when`(flashcardInteractor.getTranslation(281L)).thenReturn(translation)
        `when`(flashcardInteractor.getVocabulary(2L)).thenReturn(vocabulary)
        `when`(flashcardInteractor.getVocabularyRevisionFlashcard(4L)).thenReturn(revisionFlashcard)

        /* act */
        presenter.onAttach(flashcardView)
        presenter.start(4L)

        /* assert */
        verify(flashcardView).setContent(revisionFlashcard, vocabulary, translation)
    }

    @Test
    fun schedulesCardCorrectlyOnRemembered() {
        /* arrange */
        revisionFlashcard.nextDisplayTime = LocalDate.now()
        revisionFlashcard.interval = 1
        var queue = DailyFlashcardQueueImpl()
        queue.addFlashcard(revisionFlashcard)
        var scheduler = SpacedRepetitionSchedulerImpl()
        var presenter = MeaningCheckPresenterImpl<MeaningCheckView, MeaningCheckInteractor>(checkInteractor, queue, scheduler, preferenceHelper)
        `when`(checkInteractor.getCard(4L)).thenReturn(revisionFlashcard)

        /* act */
        presenter.onAttach(checkView)
        presenter.reviseCard(4L, true)

        /* assert */
        verify(checkInteractor).saveCard(revisionFlashcard)
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
        var presenter = MeaningCheckPresenterImpl<MeaningCheckView, MeaningCheckInteractor>(checkInteractor, queue, scheduler, preferenceHelper)
        `when`(checkInteractor.getCard(4L)).thenReturn(revisionFlashcard)

        /* act */
        presenter.onAttach(checkView)
        presenter.reviseCard(4L, false)

        /* assert */
        verify(checkInteractor).saveCard(revisionFlashcard)
        assert(!queue.isQueueEmpty())
        assert(queue.todaysFlashcards.size == 1)
        assert(queue.todaysFlashcards.contains(revisionFlashcard))
        assert(revisionFlashcard.nextDisplayTime == LocalDate.now())
        assert(revisionFlashcard.interval == 0L)
    }
}