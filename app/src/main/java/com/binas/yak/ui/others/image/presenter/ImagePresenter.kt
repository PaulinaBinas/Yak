package com.binas.yak.ui.others.image.presenter

import com.binas.yak.ui.base.presenter.Presenter
import com.binas.yak.ui.others.image.interactor.ImageInteractor
import com.binas.yak.ui.others.image.view.ImageView

interface ImagePresenter<V: ImageView, I: ImageInteractor>: Presenter<V, I> {
}