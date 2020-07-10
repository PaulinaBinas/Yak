package com.binas.yak.ui.study.grammar.reviseWriting.view

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.binas.yak.R
import com.binas.yak.data.model.grammar.Grammar
import com.binas.yak.data.model.grammar.GrammarRevisionFlashcard
import com.binas.yak.ui.base.view.BaseActivity
import com.binas.yak.ui.main.view.MainActivity
import com.binas.yak.ui.settings.view.SettingsActivity
import com.binas.yak.ui.study.common.reviseWriting.view.ReviseWritingActivity
import com.binas.yak.ui.study.grammar.reviseWriting.interactor.GrammarReviseWritingInteractor
import com.binas.yak.ui.study.grammar.reviseWriting.presenter.GrammarReviseWritingPresenter
import kotlinx.android.synthetic.main.activity_grammar_revise_writing.*
import javax.inject.Inject

class GrammarReviseWritingActivity : BaseActivity(), GrammarReviseWritingView {

    @Inject
    internal lateinit var presenter: GrammarReviseWritingPresenter<GrammarReviseWritingView,
            GrammarReviseWritingInteractor>
    private var playing: Boolean = false
    private var sentenceStart: String = ""
    private var sentenceEnd: String = ""
    private var grammar: String = ""
    private var audioFileName: String = ""
    private var id: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grammar_revise_writing)
        presenter?.onAttach(this)
        presenter?.start()
    }

    fun onClickGoToReviseWriting(view: View) {
        val intent = Intent(this, ReviseWritingActivity::class.java)
        intent.putExtra("sentenceStart", this.sentenceStart)
        intent.putExtra("sentenceEnd", this.sentenceEnd)
        intent.putExtra("grammar", grammar)
        intent.putExtra("id", id)
        intent.putExtra("type", "grammar")
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

    override fun setContent(card: GrammarRevisionFlashcard, grammar: Grammar) {
        this.grammar = grammar.grammarPhase.toString()
        this.sentenceStart = grammar.firstPartOfSentence.toString()
        this.sentenceEnd = grammar.secondPartOfSentence.toString()
        this.audioFileName = grammar.audioFileName.toString()
        this.id = card.id
        sentence.text = "$sentenceStart..."
    }

    override fun clickSoundButton() {
        playSoundButton.callOnClick()
    }
}