package com.binas.yak.ui.study.common.meaningCheck.view

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import com.binas.yak.R
import com.binas.yak.ui.base.view.BaseActivity
import com.binas.yak.ui.settings.view.SettingsActivity
import com.bumptech.glide.Glide
import com.yariksoffice.lingver.Lingver
import kotlinx.android.synthetic.main.activity_meaning_check.*
import kotlinx.android.synthetic.main.fragment_image.*
import java.security.AccessController.getContext


class MeaningCheckActivity : BaseActivity(), MeaningCheckView {

    var playing: Boolean = false
    var soundName: String = ""
    var imageName: String = ""
    var tibetanWord: String = ""
    var translation: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meaning_check)
        this.setContent()
        loadImage()
    }

    private fun setContent() {
        this.imageName = intent.getStringExtra("imageFileName").toString()
        this.soundName = intent.getStringExtra("audioFileName").toString()
        this.tibetanWord = intent.getStringExtra("word").toString()
        if(Lingver.getInstance().getLanguage().equals("pl")) {
            this.translation = intent.getStringExtra("polish").toString()
        } else {
            this.translation = intent.getStringExtra("english").toString()
        }
        tibetanWordTextView.text = this.tibetanWord
        revealTranslation.setOnTouchListener(object: View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                var context: Context? = v?.context
                var activity: Activity? = null
                while (context is ContextWrapper) {
                    if (context is Activity) {
                        activity = context
                    }
                    context = (context as ContextWrapper).baseContext
                }
                if(event?.action == MotionEvent.ACTION_DOWN) {
                    (activity as MeaningCheckView)?.setTranslation()
                }
                if(event?.action == MotionEvent.ACTION_UP) {
                    translationTextView.text = ""
                }
                return true
            }
        })
    }

    override fun onStart() {
        super.onStart()
        playSoundButton.callOnClick()
    }

    private fun loadImage() {
        Glide.with(baseContext)
            .load(resources.getIdentifier(this.imageName, "drawable", this.packageName))
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

    override fun setTranslation() {
        translationTextView.text = this.translation
    }
}