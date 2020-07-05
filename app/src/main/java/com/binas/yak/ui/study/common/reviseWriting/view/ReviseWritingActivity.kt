package com.binas.yak.ui.study.common.reviseWriting.view

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.binas.yak.R
import com.binas.yak.ui.base.view.BaseActivity
import com.binas.yak.ui.settings.view.SettingsActivity
import com.binas.yak.ui.study.common.compareWriting.view.CompareWritingActivity
import kotlinx.android.synthetic.main.fragment_drawing.*
import java.io.ByteArrayOutputStream

class ReviseWritingActivity : BaseActivity(), ReviseWritingView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_revise_writing)
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
        val intent = Intent(this, CompareWritingActivity::class.java)
        val stream = ByteArrayOutputStream()
        viewToBitmap(draw_view)?.compress(Bitmap.CompressFormat.PNG, 100, stream)
        val bytes: ByteArray = stream.toByteArray()
        intent.putExtra("bitmap", bytes)
        loadExtras(intent)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }

    private fun viewToBitmap(view: View): Bitmap? {
        val bitmap =
            Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }

    private fun loadExtras(newIntent: Intent) {
        var int = intent.extras
        if(intent.hasExtra("image")) {
            newIntent.putExtra("image", intent.getStringExtra("image"))
        } else if(intent.hasExtra("sentenceStart") && intent.hasExtra("grammar") && intent.hasExtra("sentenceEnd")) {
            newIntent.putExtra("sentenceStart", intent.getStringExtra("sentenceStart"))
            newIntent.putExtra("sentenceEnd", intent.getStringExtra("sentenceEnd"))
            newIntent.putExtra("grammar", intent.getStringExtra("grammar"))
        } else if(intent.hasExtra("word")) {
            newIntent.putExtra("word", intent.getStringExtra("word"))
        }
    }
}