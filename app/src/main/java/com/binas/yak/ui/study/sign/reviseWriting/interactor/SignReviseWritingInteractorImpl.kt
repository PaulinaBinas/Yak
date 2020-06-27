package com.binas.yak.ui.study.sign.reviseWriting.interactor

import com.binas.yak.data.preferences.PreferenceHelper
import com.binas.yak.ui.base.interactor.BaseInteractor
import javax.inject.Inject

class SignReviseWritingInteractorImpl @Inject internal constructor(preferenceHelper: PreferenceHelper):
    BaseInteractor(preferenceHelper), SignReviseWritingInteractor {
}