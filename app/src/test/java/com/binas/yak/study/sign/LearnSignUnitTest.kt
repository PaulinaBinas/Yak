package com.binas.yak.study.sign

import com.binas.yak.data.model.RevisionType
import com.binas.yak.data.model.sign.Sign
import com.binas.yak.data.model.sign.SignRevisionFlashcard
import com.binas.yak.data.model.sign.SignStudyFlashcard
import com.binas.yak.data.model.translation.Translation
import com.binas.yak.data.preferences.PreferenceHelperImpl
import com.binas.yak.ui.study.sign.learn.studyCard.interactor.SignStudyCardInteractor
import com.binas.yak.ui.study.sign.learn.studyCard.interactor.SignStudyCardInteractorImpl
import com.binas.yak.ui.study.sign.learn.studyCard.presenter.SignStudyCardPresenterImpl
import com.binas.yak.ui.study.sign.learn.studyCard.view.SignStudyCardActivity
import com.binas.yak.ui.study.sign.learn.studyCard.view.SignStudyCardView
import com.binas.yak.ui.study.sign.learn.writing.interactor.LearnSignWritingInteractor
import com.binas.yak.ui.study.sign.learn.writing.interactor.LearnSignWritingInteractorImpl
import com.binas.yak.ui.study.sign.learn.writing.presenter.LearnSignWritingPresenterImpl
import com.binas.yak.ui.study.sign.learn.writing.view.LearnSignWritingActivity
import com.binas.yak.ui.study.sign.learn.writing.view.LearnSignWritingView
import com.binas.yak.util.impl.DailyFlashcardQueueImpl
import org.junit.Test
import org.mockito.Mockito.*

class LearnSignUnitTest {

    var flashcardView = mock(SignStudyCardActivity::class.java)
    var flashcardInteractor = mock(SignStudyCardInteractorImpl::class.java)
    var learnInteractor = mock(LearnSignWritingInteractorImpl::class.java)
    var learnView = mock(LearnSignWritingActivity::class.java)
    var preferenceHelper = mock(PreferenceHelperImpl::class.java)
    var sign = Sign(1L, "ཀ", "ka", 1L)
    var card = SignStudyFlashcard(1L, 0, "", 1L)
    var translation = Translation(1L, "Wyobraź sobie niską osobę z bardzo krótkimi rękoma, która wykonuje skłon i próbuje dosięgnąć swoich stóp, ale nie może i krzyczy ze złością „to jakiś KAbaret!”. Tak wygląda tybetański znak „KA”. Spróbuj narysować go w przedstawionej kolejności, cały czas utrzymując w wyobraźni poprzedni obraz.", "Imagine a person, who is not very flexible and has short arms. Then picture them bending down to try and reach their feet, while saying “CAn’t!” in an annoyed voice. That’s the Tibetan “KA” sign. Try drawing it in the order presented, all the while keeping the image vivid in your mind.")
    var signRevisionFlashcard1 = SignRevisionFlashcard(1L, RevisionType.WRITING, 1L)
    var signRevisionFlashcard2 = SignRevisionFlashcard(2L, RevisionType.MEANING, 1L)
    var signRevisionFlashcard3 = SignRevisionFlashcard(3L, RevisionType.PRONUNCIATION, 1L)

    @Test
    fun passesDataFromInteractorToView() {
        /* arrange */
        var presenter = SignStudyCardPresenterImpl<SignStudyCardView, SignStudyCardInteractor>(flashcardInteractor)
        `when`(flashcardInteractor.getSign(1L)).thenReturn(sign)
        `when`(flashcardInteractor.getSignStudyCard(1L)).thenReturn(card)
        `when`(flashcardInteractor.getTranslation(1L)).thenReturn(translation)

        /* act */
        presenter.onAttach(flashcardView)
        presenter.start(1L)

        /* assert */
        verify(flashcardView).setContent(card, sign, translation)
    }

    @Test
    fun updatesFlashcardStatusAfterStudy() {
        /* arrange */
        var queue = DailyFlashcardQueueImpl()
        val revisionFlashcards = listOf(signRevisionFlashcard1, signRevisionFlashcard2, signRevisionFlashcard3)
        queue.addFlashcard(card)
        var presenter = LearnSignWritingPresenterImpl<LearnSignWritingView, LearnSignWritingInteractor>(learnInteractor, queue, preferenceHelper)
        `when`(learnInteractor.getAllMatchingRevisionFlashcards(anyLong())).thenReturn(revisionFlashcards)

        /* act */
        presenter.onAttach(learnView)
        presenter.markCardAsStudied(1L)
        presenter.scheduleReviewCards(1L)

        /* assert */
        verify(learnInteractor).markCardAsStudied(1L)
        assert(!queue.isQueueEmpty())
        assert(queue.todaysFlashcards.size == 3)
        assert(queue.todaysFlashcards.containsAll(revisionFlashcards))
    }
}