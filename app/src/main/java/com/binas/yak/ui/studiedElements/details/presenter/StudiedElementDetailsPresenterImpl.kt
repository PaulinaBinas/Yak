package com.binas.yak.ui.studiedElements.details.presenter

import com.binas.yak.data.model.translation.Translation
import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.studiedElements.details.interactor.StudiedElementDetailsInteractor
import com.binas.yak.ui.studiedElements.details.view.StudiedElementDetailsView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import javax.inject.Inject

class StudiedElementDetailsPresenterImpl<V: StudiedElementDetailsView, I: StudiedElementDetailsInteractor>
@Inject internal constructor(interactor: I): BasePresenter<V, I>(interactor = interactor), StudiedElementDetailsPresenter<V, I> {
    override fun getTranslation(id: Long): Translation? {
        var translation: Translation? = null
        interactor?.let {
            var coroutine = GlobalScope.launch {
                translation = it.getTranslation(id)
            }
            while (!coroutine.isCompleted){}
        }
        return translation
    }

    override fun getDaysTillRevision(type: String, id: Long): Int {
        var days = 0
        interactor?.let {
            var coroutine = GlobalScope.launch {
                if(type == "sign") {
                    var cards = it.getSignRevisionCards(id)
                    cards.sortedByDescending { "nextDisplayTime" }
                    cards = cards.filter { card -> card.nextDisplayTime != null }
                    if(cards.isNotEmpty()) {
                        days = LocalDate.now().until(cards[0].nextDisplayTime , ChronoUnit.DAYS).toInt()
                    }
                } else if(type == "vocabulary") {
                    var cards = it.getVocabularyRevisionCards(id)
                    cards.sortedByDescending { "nextDisplayTime" }
                    cards = cards.filter { card -> card.nextDisplayTime != null }
                    if(cards.isNotEmpty()) {
                        days = LocalDate.now().until(cards[0].nextDisplayTime , ChronoUnit.DAYS).toInt()
                    }
                } else if(type == "grammar") {
                    var cards = it.getGrammarRevisionCards(id)
                    cards.sortedByDescending { "nextDisplayTime" }
                    cards = cards.filter { card -> card.nextDisplayTime != null }
                    if(cards.isNotEmpty()) {
                        days = LocalDate.now().until(cards[0].nextDisplayTime , ChronoUnit.DAYS).toInt()
                    }
                }
            }
            while(!coroutine.isCompleted){}
        }
        return days
    }
}