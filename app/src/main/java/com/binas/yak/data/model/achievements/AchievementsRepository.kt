package com.binas.yak.data.model.achievements

interface AchievementsRepository {

    fun getSignsCount(): Int

    fun getStudiedSignsCount(): Int

    fun getWordsCount(): Int

    fun getStudiedWordsCount(): Int

    fun getGrammarCount(): Int

    fun getStudiedGrammarCount(): Int
}