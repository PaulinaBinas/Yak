package com.binas.yak.ui.study.grammar.learn.studyCard.view

import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import androidx.core.text.color
import com.binas.yak.R
import com.binas.yak.data.model.grammar.Grammar
import com.binas.yak.data.model.grammar.GrammarStudyFlashcard
import com.binas.yak.data.model.translation.Translation
import com.binas.yak.ui.base.view.BaseActivity
import com.binas.yak.ui.settings.view.SettingsActivity
import com.binas.yak.ui.study.grammar.learn.learnWriting.view.LearnGrammarWritingActivity
import com.binas.yak.ui.study.grammar.learn.studyCard.interactor.GrammarStudyCardInteractor
import com.binas.yak.ui.study.grammar.learn.studyCard.presenter.GrammarStudyCardPresenter
import com.yariksoffice.lingver.Lingver
import kotlinx.android.synthetic.main.activity_grammar_study_card.*
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grammar_study_card)
        presenter?.onAttach(this)
        presenter?.start()
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
        val intent = Intent(this, LearnGrammarWritingActivity::class.java)
        intent.putExtra("sentenceStart", sentenceStart)
        intent.putExtra("sentenceEnd", sentenceEnd)
        intent.putExtra("grammar", grammar)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }

    fun onClickSettingsButton(view: View) {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }

    fun onClickBackButton(view: View) {
        onBackPressed()
    }

    override fun setContent(card: GrammarStudyFlashcard, grammar: Grammar, text: Translation?) {
        this.sentenceStart = grammar.firstPartOfSentence.toString()
        this.sentenceEnd = grammar.secondPartOfSentence.toString()
        this.grammar = grammar.grammarPhase.toString()
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
}