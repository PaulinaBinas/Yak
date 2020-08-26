package com.binas.yak.study.vocabulary

import com.binas.yak.data.model.RevisionType
import com.binas.yak.data.model.translation.Translation
import com.binas.yak.data.model.vocabulary.Vocabulary
import com.binas.yak.data.model.vocabulary.VocabularyRevisionFlashcard
import com.binas.yak.data.model.vocabulary.VocabularyStudyFlashcard
import com.binas.yak.data.preferences.PreferenceHelperImpl
import com.binas.yak.ui.study.vocabulary.learn.learnWriting.interactor.LearnVocabularyWritingInteractor
import com.binas.yak.ui.study.vocabulary.learn.learnWriting.interactor.LearnVocabularyWritingInteractorImpl
import com.binas.yak.ui.study.vocabulary.learn.learnWriting.presenter.LearnVocabularyWritingPresenterImpl
import com.binas.yak.ui.study.vocabulary.learn.learnWriting.view.LearnVocabularyWritingActivity
import com.binas.yak.ui.study.vocabulary.learn.learnWriting.view.LearnVocabularyWritingView
import com.binas.yak.ui.study.vocabulary.learn.studyCard.interactor.VocabularyStudyCardInteractor
import com.binas.yak.ui.study.vocabulary.learn.studyCard.interactor.VocabularyStudyCardInteractorImpl
import com.binas.yak.ui.study.vocabulary.learn.studyCard.presenter.VocabularyStudyCardPresenterImpl
import com.binas.yak.ui.study.vocabulary.learn.studyCard.view.VocabularyStudyCardActivity
import com.binas.yak.ui.study.vocabulary.learn.studyCard.view.VocabularyStudyCardView
import com.binas.yak.util.impl.DailyFlashcardQueueImpl
import org.junit.Test
import org.mockito.Mockito.*

class LearnVocabularyUnitTest {

    var flashcardView = mock(VocabularyStudyCardActivity::class.java)
    var flashcardInteractor = mock(VocabularyStudyCardInteractorImpl::class.java)
    var learnInteractor = mock(LearnVocabularyWritingInteractorImpl::class.java)
    var learnView = mock(LearnVocabularyWritingActivity::class.java)
    var preferenceHelper = mock(PreferenceHelperImpl::class.java)
    var card = VocabularyStudyFlashcard(2L, 2L);
    var vocabulary = Vocabulary(2L, "ང་", "ime", 281);
    var translation = Translation(281L, "Ja", "I");
    var vocabularyRevisionFlashcard1 = VocabularyRevisionFlashcard(4L, 2L, RevisionType.PRONUNCIATION)
    var vocabularyRevisionFlashcard2 = VocabularyRevisionFlashcard(5L, 2L, RevisionType.WRITING)
    var vocabularyRevisionFlashcard3 = VocabularyRevisionFlashcard(6L, 2L, RevisionType.MEANING)

    @Test
    fun passesDataFromInteractorToView() {
        /* arrange */
        var presenter = VocabularyStudyCardPresenterImpl<VocabularyStudyCardView, VocabularyStudyCardInteractor>(flashcardInteractor, preferenceHelper)
        `when`(flashcardInteractor.getTranslation(anyLong())).thenReturn(translation)
        `when`(flashcardInteractor.getVocabulary(anyLong())).thenReturn(vocabulary)
        `when`(flashcardInteractor.getVocabularyStudyFlashcard(anyLong())).thenReturn(card)

        /* act */
        presenter.onAttach(flashcardView)
        presenter.start(2L)

        /* assert */
        verify(flashcardView).setContent(card, vocabulary, translation)
    }

    @Test
    fun updatesFlashcardStatusAfterStudy() {
        /* arrange */
        var queue = DailyFlashcardQueueImpl()
        var revisionFlashcards = listOf(vocabularyRevisionFlashcard1, vocabularyRevisionFlashcard2, vocabularyRevisionFlashcard3)
        queue.addFlashcard(card)
        var presenter = LearnVocabularyWritingPresenterImpl<LearnVocabularyWritingView, LearnVocabularyWritingInteractor>(learnInteractor, queue, preferenceHelper)
        `when`(learnInteractor.getAllMatchingRevisionFlashcards(2L)).thenReturn(revisionFlashcards)

        /* act */
        presenter.onAttach(learnView)
        presenter.markCardAsStudied(2L)
        presenter.scheduleReviewCards(2L)

        /* assert */
        verify(learnInteractor).markCardAsStudied(2L)
        assert(!queue.isQueueEmpty())
        assert(queue.todaysFlashcards.size == 3)
        assert(queue.todaysFlashcards.containsAll(revisionFlashcards))
    }
}