package com.binas.yak.ui.study.vocabulary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.binas.yak.R
import com.binas.yak.ui.settings.SettingsActivity
import com.binas.yak.ui.study.LearnNewSignActivity
import com.binas.yak.ui.study.LearnNewVocabularyActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_studied_elements.*
import kotlinx.android.synthetic.main.activity_vocabulary_study_card.*
import kotlinx.android.synthetic.main.fragment_image.*

class VocabularyStudyCardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vocabulary_study_card)
        loadImage()
    }

    private fun loadImage() {
        Glide.with(baseContext)
            .load(resources.getIdentifier("img1", "drawable", this.packageName))
            .into(imageStudyFragment.imageView)
    }

    fun onClickGoToLearnNewVocabulary(view: View) {
        val intent: Intent = Intent(this, LearnNewVocabularyActivity::class.java)
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