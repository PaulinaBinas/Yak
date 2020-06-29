package com.binas.yak.ui.study.common.compareWriting.view

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.color
import androidx.core.text.underline
import com.binas.yak.R
import com.binas.yak.ui.base.view.BaseActivity
import com.binas.yak.ui.settings.view.SettingsActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_compare_writing.*
import kotlinx.android.synthetic.main.fragment_image.*


class CompareWritingActivity : BaseActivity(), CompareWritingView {

    private var sentence: String = ""
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
            loadImage()
        } else if(intent.hasExtra("sentence") && intent.hasExtra("grammar")) {
            sentence = intent.getStringExtra("sentence")
            grammar = intent.getStringExtra("grammar")
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

    private fun loadImage() {
        Glide.with(baseContext)
            .load(resources.getIdentifier(imageName, "drawable", this.packageName))
            .into(imageFragmentWithReference.imageView)
    }

    fun loadGrammarText() {
        var text = SpannableStringBuilder()
            .append(sentence)
            .color(Color.rgb(100, 171, 113)) { underline { append(grammar) }}
            .append("།")
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