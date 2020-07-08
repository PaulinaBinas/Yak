package com.binas.yak.data.preferences

import android.content.Context
import javax.inject.Inject

interface PreferenceHelper {

    fun getDailyCardLimit(): Int

    fun setDailyCardLimit(limit: Int)

    fun getIsLanguageSet(): Boolean

    fun setIsLanguageSet(value: Boolean)

    fun setNumberOfElementsStudied(number: Int)

    fun getNumberOfElementsStudied(): Int
}