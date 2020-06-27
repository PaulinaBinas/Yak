package com.binas.yak.ui.study.vocabulary.learn.studyCard.view

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.binas.yak.R
import com.binas.yak.ui.base.view.BaseActivity
import com.binas.yak.ui.settings.view.SettingsActivity
import com.binas.yak.ui.study.vocabulary.learn.learnWriting.view.LearnVocabularyWritingActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_vocabulary_study_card.*
import kotlinx.android.synthetic.main.fragment_image.*

class VocabularyStudyCardActivity : BaseActivity(), VocabularyStudyCardView {

    private var playing: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vocabulary_study_card)
        loadImage()
    }

    override fun onStart() {
        super.onStart()
        playSoundButton.callOnClick()
    }

    private fun loadImage() {
        Glide.with(baseContext)
            .load(resources.getIdentifier("read", "drawable", this.packageName))
            .into(imageStudyFragment.imageView)
    }

    fun onClickGoToLearnNewVocabulary(view: View) {
        val intent: Intent = Intent(this, LearnVocabularyWritingActivity::class.java)
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

    fun onClickPlaySound(view: View) {
        if (!playing) {
            playing = true
            val mp: MediaPlayer = MediaPlayer()
            val uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.read)
            mp.setDataSource(this, uri)
            mp.prepare()
            mp.setOnPreparedListener { mp.start() }
            mp.setOnCompletionListener { playing = false }
        }
    }
}