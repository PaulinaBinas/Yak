package com.binas.yak.data.preferences

import android.content.Context
import com.binas.yak.data.model.User
import javax.inject.Inject

interface PreferenceHelper {

    fun getDailyCardLimit(): Int

    fun setDailyCardLimit(limit: Int)

    fun getIsLanguageSet(): Boolean

    fun setIsLanguageSet(value: Boolean)

    fun getCurrentUserEmail(): String?

    fun setCurrentUserEmail(userEmail: String)
}