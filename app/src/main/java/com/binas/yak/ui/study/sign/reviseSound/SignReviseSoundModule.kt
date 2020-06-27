package com.binas.yak.ui.study.sign.reviseSound

import com.binas.yak.ui.study.sign.reviseSound.interactor.SignReviseSoundInteractor
import com.binas.yak.ui.study.sign.reviseSound.interactor.SignReviseSoundInteractorImpl
import com.binas.yak.ui.study.sign.reviseSound.presenter.SignReviseSoundPresenter
import com.binas.yak.ui.study.sign.reviseSound.presenter.SignReviseSoundPresenterImpl
import com.binas.yak.ui.study.sign.reviseSound.view.SignReviseSoundView
import dagger.Module
import dagger.Provides

@Module
class SignReviseSoundModule {

    @Provides
    internal fun provideSignReviseSoundInteractor(interactor: SignReviseSoundInteractorImpl): SignReviseSoundInteractor = interactor

    @Provides
    internal fun provideSignReviseSoundPresenter(presenter: SignReviseSoundPresenterImpl<SignReviseSoundView, SignReviseSoundInteractor>)
            : SignReviseSoundPresenter<SignReviseSoundView, SignReviseSoundInteractor> = presenter
}