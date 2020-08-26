package com.binas.yak.ui.study.grammar.reviseSound.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.SystemClock
import android.text.SpannableStringBuilder
import android.view.View
import androidx.core.text.color
import com.binas.yak.R
import com.binas.yak.data.model.grammar.Grammar
import com.binas.yak.data.model.grammar.GrammarRevisionFlashcard
import com.binas.yak.data.model.translation.Translation
import com.binas.yak.ui.base.view.BaseActivity
import com.binas.yak.ui.main.view.MainActivity
import com.binas.yak.ui.settings.view.SettingsActivity
import com.binas.yak.ui.study.common.breakActivity.BreakActivity
import com.binas.yak.ui.study.grammar.reviseSound.interactor.GrammarReviseSoundInteractor
import com.binas.yak.ui.study.grammar.reviseSound.presenter.GrammarReviseSoundPresenter
import com.binas.yak.ui.study.grammar.reviseSound.pronunciationCheck.view.GrammarPronunciationCheckActivity
import com.yariksoffice.lingver.Lingver
import kotlinx.android.synthetic.main.activity_grammar_revise_sound.*
import kotlinx.android.synthetic.main.fragment_action_bar_with_timer.*
import javax.inject.Inject

open class GrammarReviseSoundActivity : BaseActivity(), GrammarReviseSoundView {

    @Inject
    lateinit var presenter: GrammarReviseSoundPresenter<GrammarReviseSoundView, GrammarReviseSoundInteractor>
    private var sentenceStart: String = ""
    private var grammar: String = ""
    private var sentenceEnd: String = ""
    private var audioFileName: String = ""
    private var translation: String = ""
    private var id: Long? = null
    private var timeLeft = 0L
    private var timeStarted = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grammar_revise_sound)
        id = intent.getLongExtra("id", -1L)
        timeLeft = intent.getLongExtra("time", 0L)
        presenter?.onAttach(this)
        presenter?.start(id!!)
        startTimer()
        timeStarted = SystemClock.elapsedRealtime()
    }

    override fun loadText() {
        var text = SpannableStringBuilder()
            .append(sentenceStart)
            .color(Color.rgb(100, 171, 113)) { append(grammar) }
            .append(sentenceEnd)
        grammarTextView.text = text
    }

    override fun setContent(card: GrammarRevisionFlashcard, grammar: Grammar, text: Translation?) {
        this.audioFileName = grammar.audioFileName.toString()
        this.sentenceStart = grammar.firstPartOfSentence.toString()
        this.grammar = grammar.grammarPhase.toString()
        this.sentenceEnd = grammar.secondPartOfSentence.toString()
        if(Lingver.getInstance().getLanguage().equals("pl")) {
            this.translation = text?.polish.toString()
        } else {
            this.translation = text?.english.toString()
        }
        this.id = card.id
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

    fun onClickGoToPronunciationCheck(view: View) {
        timeLeft = fragment.timer.base
        val intent = Intent(this, GrammarPronunciationCheckActivity::class.java)
        intent.putExtra("sentenceStart", this.sentenceStart)
        intent.putExtra("grammar", this.grammar)
        intent.putExtra("sentenceEnd", this.sentenceEnd)
        intent.putExtra("sound", this.audioFileName)
        intent.putExtra("translation", this.translation)
        intent.putExtra("id", this.id)
        intent.putExtra("time", timeLeft)
        intent.putExtra("timeStarted", timeStarted)
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
}