package com.binas.yak.ui.authentication.authMenu.presenter

import com.binas.yak.ui.authentication.authMenu.interactor.AuthMenuInteractor
import com.binas.yak.ui.authentication.authMenu.view.AuthMenuView
import com.binas.yak.ui.base.presenter.BasePresenter
import javax.inject.Inject

class AuthMenuPresenterImpl<V: AuthMenuView, I: AuthMenuInteractor> @Inject internal constructor(interactor: I): BasePresenter<V, I>(interactor = interactor), AuthMenuPresenter<V, I> {
}