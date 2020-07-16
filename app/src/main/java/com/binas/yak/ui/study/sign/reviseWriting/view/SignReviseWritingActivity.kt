package com.binas.yak.ui.study.sign.reviseWriting.view

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import com.binas.yak.R
import com.binas.yak.data.model.sign.Sign
import com.binas.yak.data.model.sign.SignRevisionFlashcard
import com.binas.yak.ui.base.view.BaseActivity
import com.binas.yak.ui.main.view.MainActivity
import com.binas.yak.ui.settings.view.SettingsActivity
import com.binas.yak.ui.study.common.breakActivity.BreakActivity
import com.binas.yak.ui.study.common.reviseWriting.view.ReviseWritingActivity
import com.binas.yak.ui.study.sign.reviseWriting.interactor.SignReviseWritingInteractor
import com.binas.yak.ui.study.sign.reviseWriting.presenter.SignReviseWritingPresenter
import kotlinx.android.synthetic.main.activity_sign_revise_writing.*
import kotlinx.android.synthetic.main.fragment_action_bar_with_timer.*
import javax.inject.Inject

class SignReviseWritingActivity : BaseActivity(), SignReviseWritingView {

    private var playing: Boolean = false
    private var imageName: String = ""
    private var audioName: String = ""
    private var id: Long? = null
    private var timeLeft = 0L
    private var timeStarted = 0L
    @Inject
    lateinit var presenter: SignReviseWritingPresenter<SignReviseWritingView, SignReviseWritingInteractor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_revise_writing)
        id = intent.getLongExtra("id", -1L)
        timeLeft = intent.getLongExtra("time", 0L)
        presenter?.onAttach(this)
        presenter?.start(id!!)
        startTimer()
        timeStarted = SystemClock.elapsedRealtime()
    }

    fun onClickGoToReviseWriting(view: View) {
        timeLeft = fragment.timer.base
        val intent = Intent(this, ReviseWritingActivity::class.java)
        intent.putExtra("image", imageName)
        intent.putExtra("id", id)
        intent.putExtra("type", "sign")
        intent.putExtra("time", timeLeft)
        intent.putExtra("timeStarted", timeStarted)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }

    fun onClickSettingsButton(view: View) {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }

    fun onClickBackButton(view: View) {
        this.onBackPressed()
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_out_bottom)
        finish()
    }

    fun onClickPlaySound(view: View) {
        var sound = resources.getIdentifier(this.audioName, "raw", packageName)
        if (!playing && sound != null) {
            playing = true
            val mp = MediaPlayer()
            val uri = Uri.parse("android.resource://$packageName/$sound")
            mp.setDataSource(this, uri)
            mp.prepare()
            mp.setOnPreparedListener { mp.start() }
            mp.setOnCompletionListener { playing = false }
        }
    }

    override fun setContent(card: SignRevisionFlashcard, sign: Sign) {
        this.audioName = sign.audioFileName.toString()
        this.imageName = audioName
        this.id = card.id
    }

    override fun clickSoundButton() {
        playSoundButton.callOnClick()
    }

    private fun startTimer() {
        fragment.timer.isCountDown = true
        fragment.timer.base = timeLeft
        fragment.timer.setOnChronometerTickListener {
            if (it.base - SystemClock.elapsedRealtime() <= 0) {
                fragment.timer.stop()
                var intent = Intent(this, BreakActivity::class.java)
                startActivity(intent)
            }
        }
        fragment.timer.start()
    }

    override fun onPause() {
        super.onPause()
        fragment.timer.stop()
    }

    override fun onRestart() {
        super.onRestart()
        fragment.timer.start()
    }
}