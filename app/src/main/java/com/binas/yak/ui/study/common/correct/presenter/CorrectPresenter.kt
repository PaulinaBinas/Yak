package com.binas.yak.ui.study.common.correct.presenter

import android.view.inputmethod.CorrectionInfo
import com.binas.yak.ui.base.presenter.Presenter
import com.binas.yak.ui.study.common.correct.interactor.CorrectInteractor
import com.binas.yak.ui.study.common.correct.view.CorrectView

interface CorrectPresenter<V: CorrectView, I: CorrectInteractor>: Presenter<V, I> {
}