package com.binas.yak.ui.study.grammar.reviseSound.pronunciationCheck.view

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.SystemClock
import android.text.SpannableStringBuilder
import android.view.MotionEvent
import android.view.View
import androidx.core.text.color
import com.binas.yak.R
import com.binas.yak.ui.base.view.BaseActivity
import com.binas.yak.ui.settings.view.SettingsActivity
import com.binas.yak.ui.study.common.breakActivity.BreakActivity
import com.binas.yak.ui.study.grammar.reviseSound.pronunciationCheck.interactor.GrammarPronunciationCheckInteractor
import com.binas.yak.ui.study.grammar.reviseSound.pronunciationCheck.presenter.GrammarPronunciationCheckPresenter
import com.binas.yak.ui.study.view.StudyActivity
import kotlinx.android.synthetic.main.activity_grammar_pronunciation_check.*
import kotlinx.android.synthetic.main.fragment_action_bar_with_timer.*
import javax.inject.Inject

open class GrammarPronunciationCheckActivity : BaseActivity(), GrammarPronunciationCheckView {

    @Inject
    lateinit var presenter: GrammarPronunciationCheckPresenter<GrammarPronunciationCheckView, GrammarPronunciationCheckInteractor>
    private var sentenceStart: String = ""
    private var sentenceEnd: String = ""
    private var grammar: String = ""
    private var soundName: String = ""
    private var translation: String = ""
    private var id: Long? = null
    private var playing: Boolean = false
    private var timeLeft = 0L
    private var timeStarted = 0L
    private var timeEnded = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grammar_pronunciation_check)
        presenter?.onAttach(this)
        this.sentenceStart = intent.getStringExtra("sentenceStart")
        this.sentenceEnd = intent.getStringExtra("sentenceEnd")
        this.grammar = intent.getStringExtra("grammar")
        this.soundName = intent.getStringExtra("sound")
        this.translation = intent.getStringExtra("translation")
        this.id = intent.getLongExtra("id", -1L)
        this.timeLeft = intent.getLongExtra("time", 0L)
        this.timeStarted = intent.getLongExtra("timeStarted", SystemClock.elapsedRealtime())
        startTimer()
        setText()
    }

    override fun onStart() {
        super.onStart()
        playSoundButton.callOnClick()
    }

    private fun setText() {
        var text = SpannableStringBuilder()
            .append(sentenceStart)
            .color(Color.rgb(100, 171, 113)) { append(grammar) }
            .append(sentenceEnd)
        grammarTextView.text = text
        revealTranslation.setOnTouchListener(object: View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                var context: Context? = v?.context
                var activity: Activity? = null
                while (context is ContextWrapper) {
                    if (context is Activity) {
                        activity = context
                    }
                    context = (context as ContextWrapper).baseContext
                }
                if(event?.action == MotionEvent.ACTION_DOWN) {
                    (activity as GrammarPronunciationCheckActivity)?.setTranslation()
                }
                if(event?.action == MotionEvent.ACTION_UP) {
                    translationTextView.text = ""
                }
                return true
            }
        })
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
            val uri = Uri.parse("android.resource://" + packageName + "/" +
                    getResources().getIdentifier(soundName, "raw", getPackageName()))
            mp.setDataSource(this, uri)
            mp.prepare()
            mp.setOnPreparedListener { mp.start() }
            mp.setOnCompletionListener { playing = false }
        }
    }

    override fun setTranslation() {
        translationTextView.text = this.translation
    }

    fun onClickCorrect(view: View) {
        timeEnded = SystemClock.elapsedRealtime()
        timeLeft = fragment.timer.base
        id?.let { presenter?.reviseCard(true, it) }
        goToStudy()
    }

    fun onClickIncorrect(view: View) {
        timeLeft = fragment.timer.base
        timeEnded = SystemClock.elapsedRealtime()
        id?.let { presenter?.reviseCard(false, it) }
        goToStudy()
    }

    private fun goToStudy() {
        val intent = Intent(this, StudyActivity::class.java)
        intent.putExtra("time", timeLeft)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
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