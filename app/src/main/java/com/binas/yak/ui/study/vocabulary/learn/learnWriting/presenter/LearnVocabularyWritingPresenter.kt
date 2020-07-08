package com.binas.yak.ui.study.vocabulary.learn.learnWriting.presenter

import com.binas.yak.ui.base.presenter.Presenter
import com.binas.yak.ui.study.vocabulary.learn.learnWriting.interactor.LearnVocabularyWritingInteractor
import com.binas.yak.ui.study.vocabulary.learn.learnWriting.view.LearnVocabularyWritingView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

interface LearnVocabularyWritingPresenter<V: LearnVocabularyWritingView, I: LearnVocabularyWritingInteractor>: Presenter<V, I> {

    fun scheduleReviewCards(id: Long)

    fun markCardAsStudied(id: Long)
}