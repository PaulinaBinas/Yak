package com.binas.yak.ui.authentication.authMenu.presenter

import com.binas.yak.ui.authentication.authMenu.interactor.AuthMenuInteractor
import com.binas.yak.ui.authentication.authMenu.view.AuthMenuView
import com.binas.yak.ui.base.presenter.Presenter

interface AuthMenuPresenter<V: AuthMenuView, I: AuthMenuInteractor>: Presenter<V, I> {
}