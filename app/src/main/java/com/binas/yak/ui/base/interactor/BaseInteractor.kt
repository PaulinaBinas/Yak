package com.binas.yak.ui.base.interactor

import com.binas.yak.data.preferences.PreferenceHelper

open class BaseInteractor(): Interactor {

    protected lateinit var prefHelper: PreferenceHelper

    constructor(prefHelper: PreferenceHelper): this() {
        this.prefHelper = prefHelper
    }
}