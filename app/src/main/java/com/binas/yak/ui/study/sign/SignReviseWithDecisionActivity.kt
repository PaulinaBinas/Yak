package com.binas.yak.ui.study.sign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.binas.yak.R
import com.binas.yak.ui.settings.SettingsActivity

class SignReviseWithDecisionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_revise_with_decision)
    }

    fun onClickSettingsButton(view: View) {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }

    fun onClickBackButton(view: View) {
        onBackPressed()
    }
}