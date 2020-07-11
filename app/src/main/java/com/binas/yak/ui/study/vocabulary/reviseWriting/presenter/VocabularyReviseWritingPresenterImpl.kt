package com.binas.yak.ui.study.vocabulary.reviseWriting.presenter

import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.study.vocabulary.reviseWriting.interactor.VocabularyReviseWritingInteractor
import com.binas.yak.ui.study.vocabulary.reviseWriting.view.VocabularyReviseWritingView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class VocabularyReviseWritingPresenterImpl<V: VocabularyReviseWritingView, I: VocabularyReviseWritingInteractor>
@Inject internal constructor(interactor: I): BasePresenter<V, I>(interactor = interactor), VocabularyReviseWritingPresenter<V, I> {

    override fun start(id: Long) {
        interactor?.let {
            var coroutine = GlobalScope.launch {
                var card = it.getVocabularyRevisionFlashcard(id)
                var vocabulary = it.getVocabulary(card.vocabularyId)
                getView()?.setContent(card, vocabulary)
            }
            while(!coroutine.isCompleted){}
            if(coroutine.isCompleted) {
                getView()?.loadImage()
            }
        }
    }
}