package com.binas.yak.data.preferences

interface PreferenceHelper {

    fun getDailyCardLimit(): Int

    fun setDailyCardLimit(limit: Int)

    fun getIsLanguageSet(): Boolean

    fun setIsLanguageSet(value: Boolean)

    fun getCurrentUserEmail(): String?

    fun setCurrentUserEmail(userEmail: String)

    fun setCurrentUserId(id: Long)

    fun getCurrentUserId(): Long
}