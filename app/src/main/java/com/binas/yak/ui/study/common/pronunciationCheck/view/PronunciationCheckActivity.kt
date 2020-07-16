package com.binas.yak.ui.study.common.pronunciationCheck.view

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.LinearLayout
import com.airbnb.lottie.LottieDrawable
import com.binas.yak.R
import com.binas.yak.ui.base.view.BaseActivity
import com.binas.yak.ui.settings.view.SettingsActivity
import com.binas.yak.ui.study.common.breakActivity.BreakActivity
import com.binas.yak.ui.study.common.pronunciationCheck.interactor.PronunciationCheckInteractor
import com.binas.yak.ui.study.common.pronunciationCheck.presenter.PronunciationCheckPresenter
import com.binas.yak.ui.study.view.StudyActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_pronunciation_check.*
import kotlinx.android.synthetic.main.fragment_action_bar_with_timer.*
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
    private var timeLeft = 0L
    private var timeStarted = 0L
    private var timeEnded = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pronunciation_check)
        presenenter?.onAttach(this)
        text = intent.getStringExtra("text")
        imageName = intent.getStringExtra("image")
        soundName = intent.getStringExtra("sound")
        animated = intent.getBooleanExtra("animated", false)
        id = intent.getLongExtra("id", -1L)
        type = intent.getStringExtra("type")
        timeLeft = intent.getLongExtra("time", 0L)
        timeStarted = intent.getLongExtra("timeStarted", SystemClock.elapsedRealtime())
        startTimer()
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
        timeEnded = SystemClock.elapsedRealtime()
        timeLeft = fragment.timer.base
        this.id?.let { this.presenenter?.reviseCard(it, this.type, true) }
        goToStudy()
    }

    fun onClickIncorrect(view: View) {
        timeEnded = SystemClock.elapsedRealtime()
        timeLeft = fragment.timer.base
        this.id?.let { this.presenenter?.reviseCard(it, this.type, false) }
        goToStudy()
    }

    private fun goToStudy() {
        val intent = Intent(this, StudyActivity::class.java)
        intent.putExtra("time", timeLeft)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }

    private fun startTimer() {
        fragment.timer.isCountDown = true
        fragment.timer.base = timeLeft
        fragment.timer.setOnChronometerTickListener {
            if (it.base - SystemClock.elapsedRealtime() <= 0) {
                fragment.timer.stop()
                var intent = Intent(this, BreakActivity::class.java)
                startActivity(intent)
            }
        }
        fragment.timer.start()
    }

    override fun onPause() {
        super.onPause()
        fragment.timer.stop()
    }

    override fun onRestart() {
        super.onRestart()
        fragment.timer.start()
    }

    override fun getDuration(): Long {
        return timeEnded - timeStarted
    }
}