package com.binas.yak.ui.study.grammar.reviseWriting.view

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.binas.yak.R
import com.binas.yak.ui.settings.view.SettingsActivity
import com.binas.yak.ui.study.common.view.ReviseWritingActivity
import com.binas.yak.ui.study.grammar.reviseWriting.interactor.GrammarReviseWritingInteractor
import com.binas.yak.ui.study.grammar.reviseWriting.presenter.GrammarReviseWritingPresenter
import kotlinx.android.synthetic.main.activity_grammar_revise_writing.*
import javax.inject.Inject

class GrammarReviseWritingActivity : AppCompatActivity() {

    @Inject
    internal lateinit var presenter: GrammarReviseWritingPresenter<GrammarReviseWritingView,
            GrammarReviseWritingInteractor>

    private var playing: Boolean = false
    var sentence: String = "ཁོང་དེབ་ཀློག་"
    var grammar: String = "གི་མི་འདུག"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grammar_revise_writing)
    }

    override fun onStart() {
        super.onStart()
        playSoundButton.callOnClick()
    }

    fun onClickGoToReviseWriting(view: View) {
        val intent = Intent(this, ReviseWritingActivity::class.java)
        intent.putExtra("sentence", sentence)
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

    fun onClickPlaySound(view: View) {
        if (!playing) {
            playing = true
            val mp: MediaPlayer = MediaPlayer()
            val uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.doesnt)
            mp.setDataSource(this, uri)
            mp.prepare()
            mp.setOnPreparedListener { mp.start() }
            mp.setOnCompletionListener { playing = false }
        }
    }
}