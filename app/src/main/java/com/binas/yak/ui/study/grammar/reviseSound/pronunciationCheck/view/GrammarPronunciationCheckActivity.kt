package com.binas.yak.ui.study.grammar.reviseSound.pronunciationCheck.view

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.MotionEvent
import android.view.View
import androidx.core.text.color
import com.binas.yak.R
import com.binas.yak.ui.base.view.BaseActivity
import com.binas.yak.ui.settings.view.SettingsActivity
import com.binas.yak.ui.study.common.meaningCheck.view.MeaningCheckView
import com.binas.yak.ui.study.grammar.reviseSound.pronunciationCheck.interactor.GrammarPronunciationCheckInteractor
import com.binas.yak.ui.study.grammar.reviseSound.pronunciationCheck.presenter.GrammarPronunciationCheckPresenter
import com.binas.yak.ui.study.view.StudyActivity
import kotlinx.android.synthetic.main.activity_grammar_pronunciation_check.*
import kotlinx.android.synthetic.main.activity_grammar_pronunciation_check.playSoundButton
import kotlinx.android.synthetic.main.activity_grammar_pronunciation_check.revealTranslation
import kotlinx.android.synthetic.main.activity_grammar_pronunciation_check.translationTextView
import javax.inject.Inject

class GrammarPronunciationCheckActivity : BaseActivity(), GrammarPronunciationCheckView {

    @Inject
    lateinit var presenter: GrammarPronunciationCheckPresenter<GrammarPronunciationCheckView, GrammarPronunciationCheckInteractor>
    private var sentenceStart: String = ""
    private var sentenceEnd: String = ""
    private var grammar: String = ""
    private var soundName: String = ""
    private var translation: String = ""
    private var id: Long = -1L
    private var playing: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grammar_pronunciation_check)
        this.sentenceStart = intent.getStringExtra("sentenceStart")
        this.sentenceEnd = intent.getStringExtra("sentenceEnd")
        this.grammar = intent.getStringExtra("grammar")
        this.soundName = intent.getStringExtra("sound")
        this.translation = intent.getStringExtra("translation")
        this.id = intent.getLongExtra("id", -1L)
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
        presenter?.reviseCard(true, id)
        goToStudy()
    }

    fun onClickIncorrect(view: View) {
        presenter?.reviseCard(false, id)
        goToStudy()
    }

    private fun goToStudy() {
        val intent = Intent(this, StudyActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }
}