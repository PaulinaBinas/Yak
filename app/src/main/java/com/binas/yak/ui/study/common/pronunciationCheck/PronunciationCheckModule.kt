package com.binas.yak.ui.study.common.pronunciationCheck

import com.binas.yak.ui.study.common.pronunciationCheck.interactor.PronunciationCheckInteractor
import com.binas.yak.ui.study.common.pronunciationCheck.interactor.PronunciationCheckInteractorImpl
import com.binas.yak.ui.study.common.pronunciationCheck.presenter.PronunciationCheckPresenter
import com.binas.yak.ui.study.common.pronunciationCheck.presenter.PronunciationCheckPresenterImpl
import com.binas.yak.ui.study.common.pronunciationCheck.view.PronunciationCheckView
import dagger.Module
import dagger.Provides

@Module
class PronunciationCheckModule {

    @Provides
    internal fun providePronunciationCheckInteractor(interactor: PronunciationCheckInteractorImpl): PronunciationCheckInteractor = interactor

    @Provides
    internal fun providePronunciationCheckPresenter(presenter: PronunciationCheckPresenterImpl<PronunciationCheckView, PronunciationCheckInteractor>)
            : PronunciationCheckPresenter< PronunciationCheckView, PronunciationCheckInteractor> = presenter
}