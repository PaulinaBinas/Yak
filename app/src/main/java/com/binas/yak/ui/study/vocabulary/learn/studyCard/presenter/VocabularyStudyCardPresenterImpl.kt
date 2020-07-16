package com.binas.yak.ui.study.vocabulary.learn.studyCard.presenter

import com.binas.yak.data.preferences.PreferenceHelper
import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.study.vocabulary.learn.studyCard.interactor.VocabularyStudyCardInteractor
import com.binas.yak.ui.study.vocabulary.learn.studyCard.view.VocabularyStudyCardView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class VocabularyStudyCardPresenterImpl<V: VocabularyStudyCardView, I: VocabularyStudyCardInteractor>
@Inject internal constructor(interactor: I, var preferenceHelper: PreferenceHelper): BasePresenter<V, I>(interactor = interactor), VocabularyStudyCardPresenter<V, I> {

    override fun start() {
        interactor?.let {
            var coroutine = GlobalScope.launch {
                var card = it.getVocabularyStudyFlashcard(1)
                var vocab = it.getVocabulary(card.vocabularyId)
                var translation = vocab.translationId?.let { it1 -> it.getTranslation(it1) }
                getView()?.setContent(card, vocab, translation)
            }
            while(!coroutine.isCompleted) { }
            if(coroutine.isCompleted) {
                getView()?.loadImage()
                getView()?.clickSoundButton()
            }
        }
    }
}