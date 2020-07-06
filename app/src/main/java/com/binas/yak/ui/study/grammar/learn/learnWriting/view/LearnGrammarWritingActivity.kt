package com.binas.yak.ui.study.grammar.learn.learnWriting.view

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import androidx.core.text.color
import com.binas.yak.R
import com.binas.yak.ui.base.view.BaseActivity
import com.binas.yak.ui.settings.view.SettingsActivity
import kotlinx.android.synthetic.main.activity_learn_grammar_writing.*

class LearnGrammarWritingActivity : BaseActivity(), LearnGrammarWritingView {

    private var sentenceStart: String = ""
    private var grammar: String = ""
    private var sentenceEnd: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn_grammar_writing)
        loadText()
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

    fun onClickGoNext(view: View) {}
}