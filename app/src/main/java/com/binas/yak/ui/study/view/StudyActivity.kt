package com.binas.yak.ui.study.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.binas.yak.R
import com.binas.yak.ui.base.view.BaseActivity
import com.binas.yak.ui.study.grammar.reviseSound.view.GrammarReviseSoundActivity
import com.binas.yak.ui.study.grammar.reviseWriting.view.GrammarReviseWritingActivity
import com.binas.yak.ui.study.grammar.learn.studyCard.view.GrammarStudyCardActivity
import com.binas.yak.ui.study.interactor.StudyInteractor
import com.binas.yak.ui.study.presenter.StudyPresenter
import com.binas.yak.ui.study.sign.reviseSound.view.SignReviseSoundActivity
import com.binas.yak.ui.study.sign.reviseWithDecision.view.SignReviseWithDecisionActivity
import com.binas.yak.ui.study.sign.reviseWriting.view.SignReviseWritingActivity
import com.binas.yak.ui.study.sign.learn.studyCard.view.SignStudyCardActivity
import com.binas.yak.ui.study.vocabulary.reviseMeaning.view.VocabularyReviseMeaningActivity
import com.binas.yak.ui.study.vocabulary.reviseSound.view.VocabularyReviseSoundActivity
import com.binas.yak.ui.study.vocabulary.reviseWriting.view.VocabularyReviseWritingActivity
import com.binas.yak.ui.study.vocabulary.learn.studyCard.view.VocabularyStudyCardActivity
import javax.inject.Inject

class StudyActivity : BaseActivity(), StudyView {

    @Inject
    lateinit var presenter: StudyPresenter<StudyView, StudyInteractor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_study)
        presenter?.onAttach(this)
        presenter?.start()
    }

    override fun onClickGoToStudySign(id: Long) {
        val intent: Intent = Intent(this, SignStudyCardActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }
    override fun onClickGoToStudyVocab(id: Long) {
        val intent: Intent = Intent(this, VocabularyStudyCardActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }
    override fun onClickGoToStudyGrammar(id: Long) {
        val intent: Intent = Intent(this, GrammarStudyCardActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }
    override fun onClickGoToSignReviseSound(id: Long) {
        val intent: Intent = Intent(this, SignReviseSoundActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }
    override fun onClickGoToSignReviseDecision(id: Long) {
        val intent: Intent = Intent(this, SignReviseWithDecisionActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }
    override fun onClickGoToSignReviseWriting(id: Long) {
        val intent: Intent = Intent(this, SignReviseWritingActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }
    override fun onClickGoToVocabReviseMeaning(id: Long) {
        val intent: Intent = Intent(this, VocabularyReviseMeaningActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }
    override fun onClickGoToVocabReviseSound(id: Long) {
        val intent: Intent = Intent(this, VocabularyReviseSoundActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }
    override fun onClickGoToVocabReviseWriting(id: Long) {
        val intent: Intent = Intent(this, VocabularyReviseWritingActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }
    override fun onClickGoToGrammarReviseSound(id: Long) {
        val intent: Intent = Intent(this, GrammarReviseSoundActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }
    override fun onClickGoToGrammarReviseWriting(id: Long) {
        val intent: Intent = Intent(this, GrammarReviseWritingActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }

    override fun displayStudyOver() {
        setContentView(R.layout.activity_study)
    }
}
