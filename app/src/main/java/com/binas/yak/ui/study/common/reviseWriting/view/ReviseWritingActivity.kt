package com.binas.yak.ui.study.common.reviseWriting.view

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import com.binas.yak.R
import com.binas.yak.ui.base.view.BaseActivity
import com.binas.yak.ui.others.drawing.view.DrawingFragment
import com.binas.yak.ui.settings.view.SettingsActivity
import com.binas.yak.ui.study.common.breakActivity.BreakActivity
import com.binas.yak.ui.study.common.compareWriting.view.CompareWritingActivity
import kotlinx.android.synthetic.main.activity_revise_writing.*
import kotlinx.android.synthetic.main.fragment_action_bar_with_timer.*
import kotlinx.android.synthetic.main.fragment_drawing.*
import java.io.ByteArrayOutputStream

class ReviseWritingActivity : BaseActivity(), ReviseWritingView {

    private var timeLeft = 0L
    private var timeStarted = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_revise_writing)
        timeLeft = intent.getLongExtra("time", 0L)
        startTimer()
        timeStarted = intent.getLongExtra("timeStarted", SystemClock.elapsedRealtime())
    }

    fun onClickSettingsButton(view: View) {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }

    fun onClickBackButton(view: View) {
        onBackPressed()
    }

    fun onClickGoNext(view: View) {
        timeLeft = loggedInActionBar.timer.base
        val intent = Intent(this, CompareWritingActivity::class.java)
        val stream = ByteArrayOutputStream()
        viewToBitmap(draw_view)?.compress(Bitmap.CompressFormat.PNG, 100, stream)
        val bytes: ByteArray = stream.toByteArray()
        intent.putExtra("bitmap", bytes)
        loadExtras(intent)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }

    private fun viewToBitmap(view: View): Bitmap? {
        val bitmap =
            Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }

    private fun loadExtras(newIntent: Intent) {
        var int = intent.extras
        if(intent.hasExtra("image")) {
            newIntent.putExtra("image", intent.getStringExtra("image"))
            newIntent.putExtra("type", "sign")
        } else if(intent.hasExtra("sentenceStart") && intent.hasExtra("grammar") && intent.hasExtra("sentenceEnd")) {
            newIntent.putExtra("sentenceStart", intent.getStringExtra("sentenceStart"))
            newIntent.putExtra("sentenceEnd", intent.getStringExtra("sentenceEnd"))
            newIntent.putExtra("grammar", intent.getStringExtra("grammar"))
            newIntent.putExtra("type", "grammar")
        } else if(intent.hasExtra("word")) {
            newIntent.putExtra("word", intent.getStringExtra("word"))
            newIntent.putExtra("type", "vocabulary")
        }
        newIntent.putExtra("id", intent.getLongExtra("id", -1L))
        newIntent.putExtra("time", timeLeft)
        newIntent.putExtra("timeStarted", timeStarted)
    }

    fun onEraserClick(view: View) {
        var fragments = drawing_fragment.fragmentManager?.fragments
        if (fragments != null) {
            for(item in fragments) {
                if(item is DrawingFragment) {
                    item.onEraserClick(view)
                }
            }
        }
    }

    private fun startTimer() {
        loggedInActionBar.timer.isCountDown = true
        loggedInActionBar.timer.base = timeLeft
        loggedInActionBar.timer.setOnChronometerTickListener {
            if (it.base - SystemClock.elapsedRealtime() <= 0) {
                loggedInActionBar.timer.stop()
                var intent = Intent(this, BreakActivity::class.java)
                startActivity(intent)
            }
        }
        loggedInActionBar.timer.start()
    }

    override fun onPause() {
        super.onPause()
        loggedInActionBar.timer.stop()
    }

    override fun onRestart() {
        super.onRestart()
        loggedInActionBar.timer.start()
    }
}