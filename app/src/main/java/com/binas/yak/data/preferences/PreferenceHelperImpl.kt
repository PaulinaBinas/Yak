package com.binas.yak.data.preferences

import android.content.Context
import android.content.SharedPreferences
import android.provider.Settings.Global.putInt
import androidx.core.content.edit
import com.binas.yak.di.PreferenceInfo
import javax.inject.Inject

class PreferenceHelperImpl @Inject constructor(context: Context, @PreferenceInfo prefFileName: String): PreferenceHelper {

    companion object {
        private val DAILY_CARD_LIMIT = "DAILY_CARD_LIMIT"
        private val LANGUAGE_SET = "IS_LANGUAGE_SET"
        private val ELEMENTS_STUDIED = "ELEMENTS_STUDIED"
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

    override fun setNumberOfElementsStudied(number: Int) {
        mPrefs.edit { putInt(ELEMENTS_STUDIED, number) }
    }

    override fun getNumberOfElementsStudied(): Int {
        return mPrefs.getInt(ELEMENTS_STUDIED, 0)
    }
}