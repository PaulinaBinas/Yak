package com.binas.yak.data.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.binas.yak.di.PreferenceInfo
import javax.inject.Inject

class PreferenceHelperImpl @Inject constructor(context: Context, @PreferenceInfo prefFileName: String): PreferenceHelper {

    companion object {
        private val DAILY_CARD_LIMIT = "DAILY_CARD_LIMIT"
        private val LANGUAGE_SET = "IS_LANGUAGE_SET"
        private val CURRENT_USER_EMAIL = "CURRENT_USER_EMAIL"
        private val CURRENT_USER_ID = "CURRENT_USER_ID"
    }

    private val mPrefs: SharedPreferences = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)

    override fun getDailyCardLimit(): Int {
        return mPrefs.getInt(DAILY_CARD_LIMIT, 15)
    }

    override fun setDailyCardLimit(limit: Int) {
        mPrefs.edit { putInt(DAILY_CARD_LIMIT, limit) }
    }

    override fun getIsLanguageSet(): Boolean {
        return mPrefs.getBoolean(LANGUAGE_SET, false)
    }

    override fun setIsLanguageSet(value: Boolean) {
        mPrefs.edit { putBoolean(LANGUAGE_SET, value) }
    }

    override fun getCurrentUserEmail(): String? {
        return mPrefs.getString(CURRENT_USER_EMAIL, "")
    }

    override fun setCurrentUserEmail(userEmail: String) {
        mPrefs.edit { putString(CURRENT_USER_EMAIL, userEmail) }
    }

    override fun setCurrentUserId(id: Long) {
        mPrefs.edit { putLong(CURRENT_USER_ID, id) }
    }

    override fun getCurrentUserId(): Long {
        return mPrefs.getLong(CURRENT_USER_ID, -1L)
    }
}