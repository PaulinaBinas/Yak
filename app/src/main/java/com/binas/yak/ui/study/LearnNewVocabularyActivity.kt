package com.binas.yak.ui.study

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.binas.yak.R
import com.binas.yak.ui.settings.SettingsActivity

class LearnNewVocabularyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn_new_vocabulary)
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