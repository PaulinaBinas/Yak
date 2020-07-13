package com.binas.yak.ui.study.common.reviseWriting.view

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.view.View
import com.binas.yak.R
import com.binas.yak.ui.base.view.BaseActivity
import com.binas.yak.ui.others.drawing.view.DrawingFragment
import com.binas.yak.ui.settings.view.SettingsActivity
import com.binas.yak.ui.study.common.compareWriting.view.CompareWritingActivity
import kotlinx.android.synthetic.main.activity_revise_writing.*
import java.io.ByteArrayOutputStream

class ReviseWritingActivity : BaseActivity(), ReviseWritingView {

    private var cardType: String = ""

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
            newIntent.putExtra("type", "sign")
        } else if(intent.hasExtra("sentenceStart") && intent.hasExtra("grammar") && intent.hasExtra("sentenceEnd")) {
            newIntent.putExtra("sentenceStart", intent.getStringExtra("sentenceStart"))
            newIntent.putExtra("sentenceEnd", intent.getStringExtra("sentenceEnd"))
            newIntent.putExtra("grammar", intent.getStringExtra("grammar"))
            newIntent.putExtra("type", "grammar")
        } else if(intent.hasExtra("word")) {
            newIntent.putExtra("word", intent.getStringExtra("word"))
            newIntent.putExtra("type", "vocabulary")
        }
        newIntent.putExtra("id", intent.getLongExtra("id", -1L))
    }

    fun onEraserClick(view: View) {
        var fragments = drawing_fragment.fragmentManager?.fragments
        if (fragments != null) {
            for(item in fragments) {
                if(item is DrawingFragment) {
                    item.onEraserClick(view)
                }
            }
        }
    }
}