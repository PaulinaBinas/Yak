package com.binas.yak.data.preferences

import android.content.Context
import javax.inject.Inject

interface PreferenceHelper {

    fun getDailyCardLimit(): Int

    fun setDailyCardLimit(limit: Int)
}