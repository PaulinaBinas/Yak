package com.binas.yak.data.preferences

import android.content.Context
import android.content.SharedPreferences
import android.provider.Settings.Global.putInt
import androidx.core.content.edit
import com.binas.yak.data.model.User
import com.binas.yak.di.PreferenceInfo
import javax.inject.Inject

class PreferenceHelperImpl @Inject constructor(context: Context, @PreferenceInfo prefFileName: String): PreferenceHelper {

    companion object {
        private val DAILY_CARD_LIMIT = "DAILY_CARD_LIMIT"
        private val LANGUAGE_SET = "IS_LANGUAGE_SET"
        private val CURRENT_USER = "CURRENT_USER"
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
        return mPrefs.getString(CURRENT_USER, "")
    }

    override fun setCurrentUserEmail(userEmail: String) {
        mPrefs.edit { putString(CURRENT_USER, userEmail) }
    }
}