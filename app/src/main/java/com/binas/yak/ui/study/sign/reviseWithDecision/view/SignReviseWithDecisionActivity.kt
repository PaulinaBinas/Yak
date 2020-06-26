package com.binas.yak.ui.study.sign.reviseWithDecision.view

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.binas.yak.R
import com.binas.yak.ui.settings.view.SettingsActivity
import com.binas.yak.ui.study.common.view.CorrectActivity
import com.binas.yak.ui.study.common.view.IncorrectActivity
import kotlinx.android.synthetic.main.activity_sign_revise_with_decision.*

class SignReviseWithDecisionActivity : AppCompatActivity() {

    private var playing: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_revise_with_decision)
        firstDecisionButton.setOnClickListener { goToCorrectScreen() }
        secondDecisionButton.setOnClickListener { goToIncorrectScreen() }
    }

    override fun onStart() {
        super.onStart()
        playSoundButton.callOnClick()
    }

    fun onClickSettingsButton(view: View) {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }

    fun onClickBackButton(view: View) {
        onBackPressed()
    }

    fun onClickPlaySound(view: View) {
        if (!playing) {
            playing = true
            val mp: MediaPlayer = MediaPlayer()
            val uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.sa)
            mp.setDataSource(this, uri)
            mp.prepare()
            mp.setOnPreparedListener { mp.start() }
            mp.setOnCompletionListener { playing = false }
        }
    }

    private fun goToCorrectScreen() {
        val intent = Intent(this, CorrectActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }

    private fun goToIncorrectScreen() {
        val intent = Intent(this, IncorrectActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }
}