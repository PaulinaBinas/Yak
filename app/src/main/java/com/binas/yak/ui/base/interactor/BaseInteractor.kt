package com.binas.yak.ui.base.interactor

import com.binas.yak.data.model.User
import com.binas.yak.data.preferences.PreferenceHelper

open class BaseInteractor(): Interactor {

    protected lateinit var prefHelper: PreferenceHelper
    protected var user: User? = null

    constructor(prefHelper: PreferenceHelper): this() {
        this.prefHelper = prefHelper
    }

    fun getCurrentUser() = user

    open fun setCurrentUser(user: User) {
        this.user = user
    }
}