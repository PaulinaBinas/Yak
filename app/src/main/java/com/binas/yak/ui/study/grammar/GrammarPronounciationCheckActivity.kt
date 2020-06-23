package com.binas.yak.ui.study.grammar

import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import androidx.core.text.bold
import androidx.core.text.color
import androidx.core.text.underline
import com.binas.yak.R
import com.binas.yak.ui.settings.SettingsActivity
import kotlinx.android.synthetic.main.activity_grammar_pronounciation_check.*

class GrammarPronounciationCheckActivity : AppCompatActivity() {

    private var sentence: String = ""
    private var grammar: String = ""
    private var soundName: String = ""
    private var playing: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grammar_pronounciation_check)
        sentence = intent.getStringExtra("sentence")
        grammar = intent.getStringExtra("grammar")
        soundName = intent.getStringExtra("sound")
        setText()
    }

    override fun onStart() {
        super.onStart()
        playSoundButton.callOnClick()
    }

    fun setText() {
        var text = SpannableStringBuilder()
            .append(sentence)
            .color(Color.rgb(100, 171, 113)) { underline { append(grammar) }}
            .append("།")
        grammarTextView.text = text
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
}