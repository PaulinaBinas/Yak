package com.binas.yak.ui.study.grammar

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import androidx.core.text.bold
import androidx.core.text.color
import androidx.core.text.underline
import com.binas.yak.R
import com.binas.yak.ui.settings.SettingsActivity
import com.binas.yak.ui.study.PronounciationCheckActivity
import kotlinx.android.synthetic.main.activity_grammar_revise_sound.*

class GrammarReviseSoundActivity : AppCompatActivity() {

    var sentence: String = "ཁོང་དེབ་ཀློག་"
    var grammar: String = "གི་མི་འདུག"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grammar_revise_sound)
        loadText()
    }

    fun loadText() {
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

    fun onClickGoToPronounciationCheck(view: View) {
        val intent = Intent(this, GrammarPronounciationCheckActivity::class.java)
        intent.putExtra("sentence", sentence)
        intent.putExtra("grammar", grammar)
        intent.putExtra("sound", "doesnt")
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }
}