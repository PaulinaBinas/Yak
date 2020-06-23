package com.binas.yak.ui.study

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.binas.yak.R
import com.binas.yak.ui.settings.SettingsActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_vocabulary_study_card.*
import kotlinx.android.synthetic.main.fragment_image.*

class MeaningCheckActivity : AppCompatActivity() {

    var playing: Boolean = false
    var soundName: String = "read"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meaning_check)
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

    fun onClickPlaySound(view: View) {
        if (!playing) {
            playing = true
            val mp: MediaPlayer = MediaPlayer()
            val uri = Uri.parse("android.resource://" + packageName + "/" +
                    getResources().getIdentifier(soundName, "raw", getPackageName()))
            mp.setDataSource(this, uri)
            mp.prepare()
            mp.setOnPreparedListener { mp.start() }
            mp.setOnCompletionListener { playing = false }
        }
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