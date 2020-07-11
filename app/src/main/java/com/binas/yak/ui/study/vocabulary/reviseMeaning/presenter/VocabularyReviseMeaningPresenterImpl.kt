package com.binas.yak.ui.study.vocabulary.reviseMeaning.presenter

import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.study.vocabulary.reviseMeaning.interactor.VocabularyReviseMeaningInteractor
import com.binas.yak.ui.study.vocabulary.reviseMeaning.view.VocabularyReviseMeaningView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class VocabularyReviseMeaningPresenterImpl<V: VocabularyReviseMeaningView, I: VocabularyReviseMeaningInteractor>
@Inject internal constructor(interactor: I): BasePresenter<V, I>(interactor = interactor), VocabularyReviseMeaningPresenter<V, I> {

    override fun start(id: Long) {
        interactor?.let {
            var coroutine = GlobalScope.launch {
                var card = it.getVocabularyRevisionFlashcard(1)
                var word = it.getVocabulary(card.vocabularyId)
                var translation = word.translationId?.let { it1 -> it.getTranslation(it1) }
                getView()?.setContent(card, word, translation)
                getView()?.clickSoundButton()
            }
        }
    }
}