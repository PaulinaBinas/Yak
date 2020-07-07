package com.binas.yak.ui.study.view

import com.binas.yak.ui.base.view.BaseView

interface StudyView: BaseView {

    fun onClickGoToStudySign(id: Long)
    fun onClickGoToStudyVocab(id: Long)
    fun onClickGoToStudyGrammar(id: Long)
    fun onClickGoToSignReviseSound(id: Long)
    fun onClickGoToSignReviseDecision(id: Long)
    fun onClickGoToSignReviseWriting(id: Long)
    fun onClickGoToVocabReviseMeaning(id: Long)
    fun onClickGoToVocabReviseSound(id: Long)
    fun onClickGoToVocabReviseWriting(id: Long)
    fun onClickGoToGrammarReviseSound(id: Long)
    fun onClickGoToGrammarReviseWriting(id: Long)
    fun displayStudyOver()
}