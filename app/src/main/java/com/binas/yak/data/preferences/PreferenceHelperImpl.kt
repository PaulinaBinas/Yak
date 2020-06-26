package com.binas.yak.data.preferences

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class PreferenceHelperImpl @Inject constructor(context: Context, prefFileName: String): PreferenceHelper {

    private val mPrefs: SharedPreferences = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)
}