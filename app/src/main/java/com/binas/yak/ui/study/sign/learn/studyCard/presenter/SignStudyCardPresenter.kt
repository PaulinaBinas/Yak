package com.binas.yak.ui.study.sign.learn.studyCard.presenter

import com.binas.yak.ui.base.presenter.Presenter
import com.binas.yak.ui.study.sign.learn.studyCard.interactor.SignStudyCardInteractor
import com.binas.yak.ui.study.sign.learn.studyCard.view.SignStudyCardView

interface SignStudyCardPresenter<V: SignStudyCardView, I: SignStudyCardInteractor>: Presenter<V, I> {

}