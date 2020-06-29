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
import com.binas.yak.ui.base.view.BaseActivity
import com.binas.yak.ui.settings.view.SettingsActivity
import com.binas.yak.ui.study.grammar.learn.learnWriting.view.LearnGrammarWritingActivity
import kotlinx.android.synthetic.main.activity_grammar_study_card.*

class GrammarStudyCardActivity : BaseActivity(), GrammarStudyCardView {

    private var sentenceStart: String = "ཁོང་དེབ་ཀློག་"
    private var grammar: String = "གི་མི་འདུག"
    private var sentenceEnd: String = "།"
    private var translation: String = "He is not reading a book."
    private var soundName: String = "doesnt"
    private var playing: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grammar_study_card)
        loadText()
        playSoundButton.callOnClick()
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
}