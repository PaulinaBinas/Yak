package com.binas.yak.ui.study.common.compareWriting.view

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import androidx.core.text.color
import com.airbnb.lottie.LottieDrawable
import com.binas.yak.R
import com.binas.yak.ui.base.view.BaseActivity
import com.binas.yak.ui.settings.view.SettingsActivity
import kotlinx.android.synthetic.main.activity_compare_writing.*
import kotlinx.android.synthetic.main.fragment_animation.*
import kotlinx.android.synthetic.main.fragment_image.*


class CompareWritingActivity : BaseActivity(), CompareWritingView {

    private var sentenceStart: String = ""
    private var sentenceEnd: String = ""
    private var grammar: String = ""
    private var imageName: String = ""
    private var word: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compare_writing)
    }

    override fun onStart() {
        super.onStart()
        val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(MATCH_PARENT, 0,
            1.4F
        )
        if(intent.hasExtra("image")) {
            imageName = intent.getStringExtra("image")
            imageLinearLayout.layoutParams = params
            loadAnimation()
        } else if(intent.hasExtra("sentenceStart") && intent.hasExtra("grammar") && intent.hasExtra("sentenceEnd")) {
            sentenceStart = intent.getStringExtra("sentenceStart")
            grammar = intent.getStringExtra("grammar")
            sentenceEnd = intent.getStringExtra("sentenceEnd")
            sentenceLinearLayout.layoutParams = params
            loadGrammarText()
        } else if(intent.hasExtra("word")) {
            word = intent.getStringExtra("word")
            sentenceLinearLayout.layoutParams = params
            sentenceTextView.text = word
        }
        val bytes = intent.getByteArrayExtra("bitmap")
        val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
        imageFragment.imageView.setImageBitmap(bitmap)
    }

    private fun loadAnimation() {
        animationFragment.animationView.setAnimation("animations/" + this.imageName + ".json")
        animationFragment.animationView.repeatCount = LottieDrawable.INFINITE
        animationFragment.animationView.speed = 1f
        animationFragment.animationView.playAnimation()
    }

    private fun loadGrammarText() {
        var text = SpannableStringBuilder()
            .append(sentenceStart)
            .color(Color.rgb(100, 171, 113)) { append(grammar) }
            .append(sentenceEnd)
        sentenceTextView.text = text
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