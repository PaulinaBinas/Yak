package com.binas.yak.ui.study.sign.learn.writing.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.binas.yak.R
import com.binas.yak.ui.base.view.BaseActivity
import com.binas.yak.ui.settings.view.SettingsActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_sign_study_card.imageFragment
import kotlinx.android.synthetic.main.fragment_image.*

class LearnSignWritingActivity : BaseActivity(), LearnSignWritingView {

    private var imageName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn_sign_writing)
        imageName = intent.getStringExtra("imageName")
        loadImage()
    }

    private fun loadImage() {
        Glide.with(baseContext)
            .load(resources.getIdentifier(imageName, "drawable", this.packageName))
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

    fun onClickGoNext(view: View) {
    }
}
