package com.binas.yak.ui.base.presenter

import com.binas.yak.ui.base.interactor.Interactor
import com.binas.yak.ui.base.view.BaseView

abstract class BasePresenter<V: BaseView, I: Interactor> internal constructor(protected var interactor: I?): Presenter<V, I> {

    private var view: V? = null

    override fun getView(): V? = view
}