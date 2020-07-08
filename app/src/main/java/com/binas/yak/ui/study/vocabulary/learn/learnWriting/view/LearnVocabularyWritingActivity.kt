package com.binas.yak.ui.study.vocabulary.learn.learnWriting.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.binas.yak.R
import com.binas.yak.ui.base.view.BaseActivity
import com.binas.yak.ui.base.view.BaseView
import com.binas.yak.ui.settings.view.SettingsActivity
import com.binas.yak.ui.study.view.StudyActivity
import kotlinx.android.synthetic.main.activity_learn_vocabulary_writing.*

class LearnVocabularyWritingActivity : BaseActivity(), LearnVocabularyWritingView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn_vocabulary_writing)
        vocabularyTextView.text = intent.getStringExtra("word")
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
        //TODO
        val intent = Intent(this, StudyActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }
}