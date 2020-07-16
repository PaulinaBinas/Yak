package com.binas.yak.ui.study.grammar.learn.studyCard.view

import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.SystemClock
import android.text.SpannableStringBuilder
import android.view.View
import androidx.core.text.color
import com.binas.yak.R
import com.binas.yak.data.model.grammar.Grammar
import com.binas.yak.data.model.grammar.GrammarStudyFlashcard
import com.binas.yak.data.model.translation.Translation
import com.binas.yak.ui.base.view.BaseActivity
import com.binas.yak.ui.main.view.MainActivity
import com.binas.yak.ui.settings.view.SettingsActivity
import com.binas.yak.ui.study.common.breakActivity.BreakActivity
import com.binas.yak.ui.study.grammar.learn.learnWriting.view.LearnGrammarWritingActivity
import com.binas.yak.ui.study.grammar.learn.studyCard.interactor.GrammarStudyCardInteractor
import com.binas.yak.ui.study.grammar.learn.studyCard.presenter.GrammarStudyCardPresenter
import com.yariksoffice.lingver.Lingver
import kotlinx.android.synthetic.main.activity_grammar_study_card.*
import kotlinx.android.synthetic.main.fragment_action_bar_with_timer.*
import javax.inject.Inject

class GrammarStudyCardActivity : BaseActivity(), GrammarStudyCardView {

    @Inject
    lateinit var presenter: GrammarStudyCardPresenter<GrammarStudyCardView, GrammarStudyCardInteractor>
    private var sentenceStart: String = ""
    private var grammar: String = ""
    private var sentenceEnd: String = ""
    private var translation: String = ""
    private var soundName: String = ""
    private var playing: Boolean = false
    private var grammarId: Long? = null
    private var timeLeft = 0L
    private var timeStarted = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grammar_study_card)
        timeLeft = intent.getLongExtra("time", 0L)
        presenter?.onAttach(this)
        presenter?.start()
        timeStarted = SystemClock.elapsedRealtime()
        startTimer()
    }

    private fun loadText() {
        sentenceTextView.text = SpannableStringBuilder()
            .append(sentenceStart)
            .color(Color.rgb(100, 171, 113)) { append(grammar) }
            .append(sentenceEnd)
        translationTextView.text = translation
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

    fun onClickGoToLearnGrammarWriting(view: View) {
        timeLeft = loggedInActionBar.timer.base
        val intent = Intent(this, LearnGrammarWritingActivity::class.java)
        intent.putExtra("sentenceStart", sentenceStart)
        intent.putExtra("sentenceEnd", sentenceEnd)
        intent.putExtra("grammar", grammar)
        intent.putExtra("id", grammarId)
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

    override fun setContent(card: GrammarStudyFlashcard, grammar: Grammar, text: Translation?) {
        this.sentenceStart = grammar.firstPartOfSentence.toString()
        this.sentenceEnd = grammar.secondPartOfSentence.toString()
        this.grammar = grammar.grammarPhase.toString()
        this.grammarId = grammar.id
        if(Lingver.getInstance().getLanguage().equals("pl")) {
            this.translation = text?.polish.toString()
        } else {
            this.translation = text?.english.toString()
        }
        this.soundName = grammar.audioFileName.toString()
        loadText()
    }

    override fun clickPlaySound() {
        playSoundButton.callOnClick()
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