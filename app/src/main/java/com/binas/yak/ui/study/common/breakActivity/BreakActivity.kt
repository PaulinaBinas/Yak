package com.binas.yak.ui.study.common.breakActivity

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import com.binas.yak.R
import com.binas.yak.ui.base.view.BaseActivity
import com.binas.yak.ui.main.view.MainActivity
import com.binas.yak.ui.settings.view.SettingsActivity
import com.binas.yak.ui.study.view.StudyActivity
import kotlinx.android.synthetic.main.activity_break.*
import kotlinx.android.synthetic.main.fragment_action_bar_with_timer.timer
import java.time.LocalTime
import java.time.temporal.ChronoUnit

class BreakActivity : BaseActivity() {

    private var timeLeft = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_break)
        timeLeft = intent.getLongExtra("time", 0L)
        startTimer()
        setTime()
    }

    private fun setTime() {
        var time = LocalTime.now().plus(timeLeft, ChronoUnit.MILLIS)
        message.text = message.text.toString() + time.hour + ":" + time.minute
    }

    private fun startTimer() {
        timer.isCountDown = true
        timer.base = SystemClock.elapsedRealtime() + timeLeft
        timer.setOnChronometerTickListener {
            timeLeft = timer.base - SystemClock.elapsedRealtime()
            if (it.base - SystemClock.elapsedRealtime() <= 0) {
                timer.stop()
                var intent = Intent(this, StudyActivity::class.java)
                intent.putExtra("time", 25 * 60 * 1000L)
                startActivity(intent)
            }
        }
        timer.start()
    }

    override fun onPause() {
        super.onPause()
        timer.stop()
    }

    override fun onRestart() {
        super.onRestart()
        timer.start()
    }

    override fun onBackPressed() {
        this.onClickBackButton(this.timer)
    }

    fun onClickBackButton(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_out_bottom)
    }

    fun onClickSettingsButton(view: View) {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_out_bottom)
    }
}