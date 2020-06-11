package com.binas.yak

import android.app.Application
import com.yariksoffice.lingver.Lingver

class CustomApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Lingver.init(this, LANGUAGE_ENGLISH)
    }

    companion object {
        const val LANGUAGE_ENGLISH = "en"
        const val LANGUAGE_ENGLISH_COUNTRY = "US"
        const val LANGUAGE_POLISH = "pl"
        const val LANGUAGE_POLISH_COUNTRY = "PL"
    }
}