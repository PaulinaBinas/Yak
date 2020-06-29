package com.binas.yak.ui.study.sign.learn.studyCard.presenter

import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.study.sign.learn.studyCard.interactor.SignStudyCardInteractor
import com.binas.yak.ui.study.sign.learn.studyCard.view.SignStudyCardView
import javax.inject.Inject

class SignStudyCardPresenterImpl<V: SignStudyCardView, I: SignStudyCardInteractor>
@Inject internal constructor(interactor: I): BasePresenter<V, I>(interactor = interactor), SignStudyCardPresenter<V, I> {
}