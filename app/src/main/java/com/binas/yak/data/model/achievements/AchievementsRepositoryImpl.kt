package com.binas.yak.data.model.achievements

import javax.inject.Inject

class AchievementsRepositoryImpl @Inject internal constructor(var achievementsDao: AchievementsDao): AchievementsRepository {
    override fun getSignsCount(): Int {
        return achievementsDao.getCoundOfAllSigns()
    }

    override fun getStudiedSignsCount(): Int {
        return achievementsDao.getCountOfAllStudiedSigns()
    }

    override fun getWordsCount(): Int {
        return achievementsDao.getCountOfAllWords()
    }

    override fun getStudiedWordsCount(): Int {
        return achievementsDao.getCountOfAllStudiedWords()
    }

    override fun getGrammarCount(): Int {
        return achievementsDao.getCountOfAllGrammar()
    }

    override fun getStudiedGrammarCount(): Int {
        return achievementsDao.getCountOfAllStudiedGrammar()
    }


}