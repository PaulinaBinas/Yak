package com.binas.yak.ui.settings.changeLanguage.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.binas.yak.R
import com.binas.yak.ui.base.view.BaseActivity
import com.binas.yak.ui.main.view.MainActivity
import com.binas.yak.ui.settings.view.SettingsActivity
import com.yariksoffice.lingver.Lingver

class ChangeLanguageActivity : BaseActivity(), ChangeLanguageView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_language)
    }

    fun onClickSettingsButton(view: View) {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

    fun onClickBackButton(view: View) {
        onBackPressed()
    }

    fun onClickEnglish(view: View) {
        changeLanguage("en", "US")
    }

    fun onClickPolish(view: View) {
        changeLanguage("pl", "PL")
    }

    private fun changeLanguage(lang: String, country: String) {
        Lingver.getInstance().setLocale(this, lang, country)
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_out_bottom)
        finish()
    }
}
