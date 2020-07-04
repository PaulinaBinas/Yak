package com.binas.yak.ui.achievements.view

import com.binas.yak.ui.base.view.BaseView

interface AchievementsView: BaseView {

    fun setContent(signsStudied: Int, signsNo: Int, wordsStudied: Int, wordsNo: Int, grammarStudied:Int, grammarNo: Int)
}