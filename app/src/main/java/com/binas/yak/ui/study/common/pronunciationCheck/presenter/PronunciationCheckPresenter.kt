package com.binas.yak.ui.study.common.pronunciationCheck.presenter

import com.binas.yak.ui.base.presenter.Presenter
import com.binas.yak.ui.study.common.pronunciationCheck.interactor.PronunciationCheckInteractor
import com.binas.yak.ui.study.common.pronunciationCheck.view.PronunciationCheckView

interface PronunciationCheckPresenter<V: PronunciationCheckView, I: PronunciationCheckInteractor>: Presenter<V, I> {

    fun reviseCard(id: Long, type: String, remembered: Boolean)
}