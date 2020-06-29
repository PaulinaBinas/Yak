package com.binas.yak.ui.others.image.presenter

import com.binas.yak.ui.base.presenter.BasePresenter
import com.binas.yak.ui.others.image.interactor.ImageInteractor
import com.binas.yak.ui.others.image.view.ImageView
import javax.inject.Inject

class ImagePresenterImpl<V: ImageView, I: ImageInteractor>
@Inject internal constructor(interactor: I): BasePresenter<V, I>(interactor = interactor), ImagePresenter<V, I> {
}