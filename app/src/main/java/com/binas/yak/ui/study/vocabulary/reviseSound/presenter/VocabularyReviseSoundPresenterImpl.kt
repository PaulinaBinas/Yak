package com.binas.yak.ui.study.vocabulary.reviseSound.presenter

import com.binas.yak.data.model.vocabulary.Vocabulary
import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.study.vocabulary.reviseSound.interactor.VocabularyReviseSoundInteractor
import com.binas.yak.ui.study.vocabulary.reviseSound.view.VocabularyReviseSoundView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class VocabularyReviseSoundPresenterImpl<V: VocabularyReviseSoundView, I: VocabularyReviseSoundInteractor>
@Inject internal constructor(interactor: I): BasePresenter<V, I>(interactor = interactor), VocabularyReviseSoundPresenter<V, I> {

    override fun start(id: Long) {
        interactor?.let {
            var coroutine = GlobalScope.launch {
                var card = it.getVocabularyRevisionFlashcard(id)
                var vocabulary = it.getVocabulary(card.vocabularyId)
                getView()?.setContent(card, vocabulary)
            }
            while (!coroutine.isCompleted){}
            if(coroutine.isCompleted) {
                getView()?.loadImage()
            }
        }
    }
}