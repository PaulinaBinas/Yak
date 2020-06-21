package com.binas.yak.ui.study

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.binas.yak.R
import com.binas.yak.ui.study.grammar.GrammarReviseSoundActivity
import com.binas.yak.ui.study.grammar.GrammarReviseWritingActivity
import com.binas.yak.ui.study.grammar.GrammarStudyCardActivity
import com.binas.yak.ui.study.sign.SignReviseSoundActivity
import com.binas.yak.ui.study.sign.SignReviseWithDecisionActivity
import com.binas.yak.ui.study.sign.SignReviseWritingActivity
import com.binas.yak.ui.study.sign.SignStudyCardActivity
import com.binas.yak.ui.study.vocabulary.VocabularyReviseMeaningActivity
import com.binas.yak.ui.study.vocabulary.VocabularyReviseSoundActivity
import com.binas.yak.ui.study.vocabulary.VocabularyReviseWritingActivity
import com.binas.yak.ui.study.vocabulary.VocabularyStudyCardActivity
import java.util.*

class StudyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_study)
    }

    fun onClickGoToStudySign(view: View) {
        val intent: Intent = Intent(this, SignStudyCardActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }
    fun onClickGoToStudyVocab(view: View) {
        val intent: Intent = Intent(this, VocabularyStudyCardActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }
    fun onClickGoToStudyGrammar(view: View) {
        val intent: Intent = Intent(this, GrammarStudyCardActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }
    fun onClickGoToSignReviseSound(view: View) {
        val intent: Intent = Intent(this, SignReviseSoundActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }
    fun onClickGoToSignReviseDecision(view: View) {
        val intent: Intent = Intent(this, SignReviseWithDecisionActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }
    fun onClickGoToSignReviseWriting(view: View) {
        val intent: Intent = Intent(this, SignReviseWritingActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }
    fun onClickGoToVocabReviseMeaning(view: View) {
        val intent: Intent = Intent(this, VocabularyReviseMeaningActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }
    fun onClickGoToVocabReviseSound(view: View) {
        val intent: Intent = Intent(this, VocabularyReviseSoundActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }
    fun onClickGoToVocabReviseWriting(view: View) {
        val intent: Intent = Intent(this, VocabularyReviseWritingActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }
    fun onClickGoToGrammarReviseSound(view: View) {
        val intent: Intent = Intent(this, GrammarReviseSoundActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }
    fun onClickGoToGrammarReviseWriting(view: View) {
        val intent: Intent = Intent(this, GrammarReviseWritingActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }
}
