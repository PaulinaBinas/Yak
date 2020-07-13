package com.binas.yak.ui.study.grammar.learn.learnWriting.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import androidx.core.text.color
import com.binas.yak.R
import com.binas.yak.ui.base.view.BaseActivity
import com.binas.yak.ui.others.drawing.view.DrawingFragment
import com.binas.yak.ui.settings.view.SettingsActivity
import com.binas.yak.ui.study.grammar.learn.learnWriting.interactor.LearnGrammarWritingInteractor
import com.binas.yak.ui.study.grammar.learn.learnWriting.presenter.LearnGrammarWritingPresenter
import com.binas.yak.ui.study.view.StudyActivity
import kotlinx.android.synthetic.main.activity_learn_grammar_writing.*
import javax.inject.Inject

class LearnGrammarWritingActivity : BaseActivity(), LearnGrammarWritingView {

    @Inject
    lateinit var presenter: LearnGrammarWritingPresenter<LearnGrammarWritingView, LearnGrammarWritingInteractor>
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

    fun onClickGoNext(view: View) {
        var id = intent.getLongExtra("id", -1L)
        presenter?.markCardAsStudied(id)
        presenter?.scheduleReviewCards(id)
        val intent = Intent(this, StudyActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }

    fun onEraserClick(view: View) {
        var fragments = drawing_fragment.fragmentManager?.fragments
        if (fragments != null) {
            for(item in fragments) {
                if(item is DrawingFragment) {
                    item.onEraserClick(view)
                }
            }
        }
    }
}