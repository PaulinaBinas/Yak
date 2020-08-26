package com.binas.yak.ui.study.grammar.learn.learnWriting.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.SystemClock
import android.text.SpannableStringBuilder
import android.view.View
import androidx.core.text.color
import com.binas.yak.R
import com.binas.yak.ui.base.view.BaseActivity
import com.binas.yak.ui.others.drawing.view.DrawingFragment
import com.binas.yak.ui.settings.view.SettingsActivity
import com.binas.yak.ui.study.common.breakActivity.BreakActivity
import com.binas.yak.ui.study.grammar.learn.learnWriting.interactor.LearnGrammarWritingInteractor
import com.binas.yak.ui.study.grammar.learn.learnWriting.presenter.LearnGrammarWritingPresenter
import com.binas.yak.ui.study.view.StudyActivity
import kotlinx.android.synthetic.main.activity_learn_grammar_writing.*
import kotlinx.android.synthetic.main.fragment_action_bar_with_timer.*
import javax.inject.Inject

open class LearnGrammarWritingActivity : BaseActivity(), LearnGrammarWritingView {

    @Inject
    lateinit var presenter: LearnGrammarWritingPresenter<LearnGrammarWritingView, LearnGrammarWritingInteractor>
    private var sentenceStart: String = ""
    private var grammar: String = ""
    private var sentenceEnd: String = ""
    private var timeLeft = 0L
    private var timeStarted = 0L
    private var timeEnded = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn_grammar_writing)
        presenter.onAttach(this)
        timeLeft = intent.getLongExtra("time", 0L)
        loadText()
        startTimer()
        timeStarted = intent.getLongExtra("timeStarted", SystemClock.elapsedRealtime())
    }

    private fun loadText() {
        sentenceStart = intent.getStringExtra("sentenceStart")
        sentenceEnd = intent.getStringExtra("sentenceEnd")
        grammar = intent.getStringExtra("grammar")
        sentenceTextView.text = SpannableStringBuilder()
            .append(sentenceStart)
            .color(Color.rgb(100, 171, 113)) { append(grammar) }
            .append(sentenceEnd)
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
        timeEnded = SystemClock.elapsedRealtime()
        timeLeft = loggedInActionBar.timer.base
        var id = intent.getLongExtra("id", -1L)
        presenter?.markCardAsStudied(id)
        presenter?.scheduleReviewCards(id)
        val intent = Intent(this, StudyActivity::class.java)
        intent.putExtra("time", timeLeft)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
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

    override fun getTimeLeft(): Long = this.timeLeft

    override fun getDuration(): Long {
        return timeEnded - timeStarted
    }
}