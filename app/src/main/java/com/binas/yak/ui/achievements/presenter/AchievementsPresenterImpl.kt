package com.binas.yak.ui.achievements.presenter

import com.binas.yak.ui.achievements.interactor.AchievementsInteractor
import com.binas.yak.ui.achievements.view.AchievementsView
import com.binas.yak.ui.base.presenter.BasePresenter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class AchievementsPresenterImpl<V: AchievementsView, I: AchievementsInteractor>
@Inject internal constructor(interactor: I): BasePresenter<V, I>(interactor = interactor), AchievementsPresenter<V, I> {

    override fun start() {
        interactor?.let {
            GlobalScope.launch {
                var signsStudied = it.getSignsStudied()
                var signs = it.getSignsTotal()
                var wordsStudied = it.getWordsStudied()
                var words = it.getWordsTotal()
                var grammarStudied = it.getGrammarStudied()
                var grammar = it.getGrammarTotal()
                getView()?.setContent(signsStudied, signs, wordsStudied, words, grammarStudied, grammar)
            }
        }
    }
}