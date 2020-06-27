package com.binas.yak.ui.study.vocabulary.learn.learnWriting.presenter

import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.study.vocabulary.learn.learnWriting.interactor.LearnVocabularyWritingInteractor
import com.binas.yak.ui.study.vocabulary.learn.learnWriting.view.LearnVocabularyWritingView
import javax.inject.Inject

class LearnVocabularyWritingPresenterImpl<V: LearnVocabularyWritingView, I: LearnVocabularyWritingInteractor>
@Inject internal constructor(interactor: I): BasePresenter<V, I>(interactor = interactor), LearnVocabularyWritingPresenter<V, I> {
}