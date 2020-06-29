package com.binas.yak.ui.study.common.pronunciationCheck.presenter

import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.study.common.pronunciationCheck.interactor.PronunciationCheckInteractor
import com.binas.yak.ui.study.common.pronunciationCheck.view.PronunciationCheckView
import javax.inject.Inject

class PronunciationCheckPresenterImpl<V: PronunciationCheckView, I: PronunciationCheckInteractor>
@Inject internal constructor(interactor: I): BasePresenter<V, I>(interactor = interactor), PronunciationCheckPresenter<V, I> {
}