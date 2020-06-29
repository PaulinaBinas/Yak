package com.binas.yak.ui.study.common.meaningCheck

import com.binas.yak.ui.study.common.meaningCheck.interactor.MeaningCheckInteractor
import com.binas.yak.ui.study.common.meaningCheck.interactor.MeaningCheckInteractorImpl
import com.binas.yak.ui.study.common.meaningCheck.presenter.MeaningCheckPresenter
import com.binas.yak.ui.study.common.meaningCheck.presenter.MeaningCheckPresenterImpl
import com.binas.yak.ui.study.common.meaningCheck.view.MeaningCheckView
import dagger.Module
import dagger.Provides

@Module
class MeaningCheckModule {

    @Provides
    internal fun provideMeaningCheckInteractor(interactor: MeaningCheckInteractorImpl): MeaningCheckInteractor = interactor

    @Provides
    internal fun provideMeaningCheckPresenter(presenter: MeaningCheckPresenterImpl<MeaningCheckView, MeaningCheckInteractor>)
            : MeaningCheckPresenter<MeaningCheckView, MeaningCheckInteractor> = presenter
}