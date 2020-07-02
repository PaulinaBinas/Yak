package com.binas.yak.ui.base.presenter

import com.binas.yak.ui.base.interactor.Interactor
import com.binas.yak.ui.base.view.BaseView

interface Presenter<V: BaseView, I: Interactor> {

    fun getView(): V?

    fun onAttach(view: V?)
}