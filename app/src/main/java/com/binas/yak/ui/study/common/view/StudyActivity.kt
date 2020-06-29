package com.binas.yak.ui.study.common.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.binas.yak.R
import com.binas.yak.ui.study.grammar.reviseSound.view.GrammarReviseSoundActivity
import com.binas.yak.ui.study.grammar.reviseWriting.view.GrammarReviseWritingActivity
import com.binas.yak.ui.study.grammar.learn.studyCard.view.GrammarStudyCardActivity
import com.binas.yak.ui.study.sign.reviseSound.view.SignReviseSoundActivity
import com.binas.yak.ui.study.sign.reviseWithDecision.view.SignReviseWithDecisionActivity
import com.binas.yak.ui.study.sign.reviseWriting.view.SignReviseWritingActivity
import com.binas.yak.ui.study.sign.learn.view.SignStudyCardActivity
import com.binas.yak.ui.study.vocabulary.reviseMeaning.view.VocabularyReviseMeaningActivity
import com.binas.yak.ui.study.vocabulary.reviseSound.view.VocabularyReviseSoundActivity
import com.binas.yak.ui.study.vocabulary.reviseWriting.view.VocabularyReviseWritingActivity
import com.binas.yak.ui.study.vocabulary.learn.studyCard.view.VocabularyStudyCardActivity

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
