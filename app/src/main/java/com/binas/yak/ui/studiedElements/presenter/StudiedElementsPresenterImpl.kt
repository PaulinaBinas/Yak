package com.binas.yak.ui.studiedElements.presenter

import android.provider.Settings
import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.studiedElements.interactor.StudiedElementsInteractor
import com.binas.yak.ui.studiedElements.view.StudiedElementsView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class StudiedElementsPresenterImpl<V: StudiedElementsView, I: StudiedElementsInteractor>
@Inject internal constructor(interactor: I): BasePresenter<V, I>(interactor = interactor), StudiedElementsPresenter<V, I> {

    override fun start() {
        interactor?.let {
            var coroutine = GlobalScope.launch {
                var signCards = it.getLearntSignCards()
                var vocabularyCards = it.getLearntVocabularyCards()
                var grammarCards = it.getLearntGrammarCards()

            }
        }
    }
}