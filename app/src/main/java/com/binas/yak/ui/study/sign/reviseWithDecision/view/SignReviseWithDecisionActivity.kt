package com.binas.yak.ui.study.sign.reviseWithDecision.view

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
import com.binas.yak.ui.study.common.correct.view.CorrectActivity
import com.binas.yak.ui.study.common.incorrect.view.IncorrectActivity
import com.binas.yak.ui.study.sign.reviseWithDecision.interactor.SignReviseWithDecisionInteractor
import com.binas.yak.ui.study.sign.reviseWithDecision.presenter.SignReviseWithDecisionPresenter
import kotlinx.android.synthetic.main.activity_sign_revise_with_decision.*
import kotlinx.android.synthetic.main.fragment_action_bar_with_timer.*
import javax.inject.Inject
import kotlin.random.Random

open class SignReviseWithDecisionActivity : BaseActivity(), SignReviseWithDecisionView {

    private var playing: Boolean = false
    private var audioFileName: String = ""
    private var correctSign: String = ""
    private var incorrectSign: String = ""
    private var id: Long? = null
    private var timeLeft = 0L
    private var timeStarted = 0L
    private var timeEnded = 0L
    @Inject
    lateinit var presenter: SignReviseWithDecisionPresenter<SignReviseWithDecisionView, SignReviseWithDecisionInteractor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_revise_with_decision)
        id = intent.getLongExtra("id", -1L)
        timeLeft = intent.getLongExtra("time", 0L)
        presenter?.onAttach(this)
        presenter?.start(id!!)
        startTimer()
        timeStarted = SystemClock.elapsedRealtime()
    }

    override fun clickSoundButton() {
        playSoundButton.callOnClick()
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

    private fun prepareButtons() {
        var correctButton = Random.nextInt(2)

        if (correctButton == 0) {
            firstDecisionButton.text = this.correctSign
            firstDecisionButton.setOnClickListener { this.goToCorrectScreen() }
            secondDecisionButton.text = this.incorrectSign
            secondDecisionButton.setOnClickListener { this.goToIncorrectScreen() }
        } else {
            firstDecisionButton.text = this.incorrectSign
            firstDecisionButton.setOnClickListener { this.goToIncorrectScreen() }
            secondDecisionButton.text = this.correctSign
            secondDecisionButton.setOnClickListener { this.goToCorrectScreen() }
        }
    }

    fun onClickPlaySound(view: View) {
        var sound = resources.getIdentifier(this.audioFileName, "raw", packageName)
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

    private fun goToCorrectScreen() {
        timeEnded = SystemClock.elapsedRealtime()
        timeLeft = fragment.timer.base
        this.id?.let { this.presenter?.reviseCard(it, true) }
        val intent = Intent(this, CorrectActivity::class.java)
        intent.putExtra("time", timeLeft)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }

    private fun goToIncorrectScreen() {
        timeEnded = SystemClock.elapsedRealtime()
        timeLeft = fragment.timer.base
        this.id?.let { this.presenter?.reviseCard(it, false) }
        val intent = Intent(this, IncorrectActivity::class.java)
        intent.putExtra("time", timeLeft)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }

    override fun setContent(card: SignRevisionFlashcard, sign: Sign, incorrectSign: Sign?) {
        this.audioFileName = sign.audioFileName.toString()
        this.correctSign = sign.tibetanSign.toString()
        this.incorrectSign = incorrectSign?.tibetanSign.toString()
        this.id = card.id
        this.prepareButtons()
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

    override fun getDuration(): Long {
        return timeEnded - timeStarted
    }
}