package com.binas.yak.ui.study.vocabulary.reviseSound.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.binas.yak.R
import com.binas.yak.ui.settings.view.SettingsActivity
import com.binas.yak.ui.study.common.view.PronounciationCheckActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_vocabulary_revise_sound.*
import kotlinx.android.synthetic.main.fragment_image.*

class VocabularyReviseSoundActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vocabulary_revise_sound)
        loadImage()
    }

    private fun loadImage() {
        Glide.with(baseContext)
            .load(resources.getIdentifier("read", "drawable", this.packageName))
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
        intent.putExtra("text", "ཀློག་")
        intent.putExtra("image", "read")
        intent.putExtra("sound", "read")
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }
}