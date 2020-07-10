package com.binas.yak.ui.study.common.pronunciationCheck.view

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import com.airbnb.lottie.LottieDrawable
import com.binas.yak.R
import com.binas.yak.ui.base.view.BaseActivity
import com.binas.yak.ui.main.view.MainActivity
import com.binas.yak.ui.settings.view.SettingsActivity
import com.binas.yak.ui.study.common.pronunciationCheck.interactor.PronunciationCheckInteractor
import com.binas.yak.ui.study.common.pronunciationCheck.presenter.PronunciationCheckPresenter
import com.binas.yak.ui.study.view.StudyActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_pronunciation_check.*
import kotlinx.android.synthetic.main.activity_vocabulary_revise_sound.*
import kotlinx.android.synthetic.main.fragment_animation.*
import kotlinx.android.synthetic.main.fragment_image.*
import javax.inject.Inject

class PronunciationCheckActivity : BaseActivity(), PronunciationCheckView {

    @Inject
    lateinit var presenenter: PronunciationCheckPresenter<PronunciationCheckView, PronunciationCheckInteractor>
    private var playing: Boolean = false
    private var text: String = ""
    private var imageName: String = ""
    private var animated: Boolean = false
    private var soundName: String = ""
    private var id: Long? = null
    private var type: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pronunciation_check)
        text = intent.getStringExtra("text")
        imageName = intent.getStringExtra("image")
        soundName = intent.getStringExtra("sound")
        animated = intent.getBooleanExtra("animated", false)
        id = intent.getLongExtra("id", -1L)
        type = intent.getStringExtra("type")
        if(animated) {
            loadAnimationAndText()
        } else {
            loadImage()
            textView.text = text
        }
    }

    override fun onStart() {
        super.onStart()
        playSoundButton.callOnClick()
    }

    private fun loadAnimationAndText() {
        animationFragment.animationView.setAnimation("animations/" + this.imageName + ".json")
        animationFragment.animationView.repeatCount = LottieDrawable.INFINITE
        animationFragment.animationView.speed = 1f
        animationFragment.animationView.playAnimation()
        var params = animationFragment.view?.layoutParams as LinearLayout.LayoutParams
        params.weight = 3.2F
        animationFragment.view?.layoutParams = params
        textView.text = text
    }

    private fun loadImage() {
        var params = image.view?.layoutParams as LinearLayout.LayoutParams
        params.weight = 3.2F
        image.view?.layoutParams = params
        Glide.with(baseContext)
            .load(resources.getIdentifier(imageName, "drawable", this.packageName))
            .into(image.imageView)
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
            val uri = Uri.parse("android.resource://" + packageName + "/" +
                    getResources().getIdentifier(soundName, "raw", getPackageName()))
            mp.setDataSource(this, uri)
            mp.prepare()
            mp.setOnPreparedListener { mp.start() }
            mp.setOnCompletionListener { playing = false }
        }
    }

    fun onClickCorrect(view: View) {
        this.id?.let { this.presenenter?.reviseCard(it, this.type, true) }
        goToStudy()
    }

    fun onClickIncorrect(view: View) {
        this.id?.let { this.presenenter?.reviseCard(it, this.type, false) }
        goToStudy()
    }

    private fun goToStudy() {
        val intent = Intent(this, StudyActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }
}