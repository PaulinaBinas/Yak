package com.binas.yak.ui.study.grammar.reviseWriting.interactor

import com.binas.yak.data.preferences.PreferenceHelper
import com.binas.yak.ui.base.interactor.BaseInteractor
import javax.inject.Inject

class GrammarReviseWritingInteractorImpl @Inject internal constructor(preferenceHelper: PreferenceHelper):
    BaseInteractor(preferenceHelper), GrammarReviseWritingInteractor {
}