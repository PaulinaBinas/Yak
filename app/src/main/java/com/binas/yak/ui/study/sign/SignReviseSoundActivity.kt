package com.binas.yak.ui.study.sign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.binas.yak.R
import com.binas.yak.ui.settings.SettingsActivity
import com.binas.yak.ui.study.PronounciationCheckActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_sign_study_card.*
import kotlinx.android.synthetic.main.fragment_image.*

class SignReviseSoundActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_revise_sound)
        loadImage()
    }

    private fun loadImage() {
        Glide.with(baseContext)
            .load(resources.getIdentifier("sa", "drawable", this.packageName))
            .into(imageFragment.imageView)
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
        val intent = Intent(this, PronounciationCheckActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }
}