package com.binas.yak.ui.achievements.interactor

import com.binas.yak.ui.base.interactor.Interactor

interface AchievementsInteractor: Interactor {

    fun getSignsStudied(): Int

    fun getSignsTotal(): Int

    fun getWordsStudied(): Int

    fun getWordsTotal(): Int

    fun getGrammarStudied(): Int

    fun getGrammarTotal(): Int
}