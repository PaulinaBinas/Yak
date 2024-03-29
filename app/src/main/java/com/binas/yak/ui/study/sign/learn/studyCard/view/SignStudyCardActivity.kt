package com.binas.yak.ui.study.sign.learn.studyCard.view

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import com.airbnb.lottie.LottieDrawable
import com.binas.yak.R
import com.binas.yak.data.model.sign.Sign
import com.binas.yak.data.model.sign.SignStudyFlashcard
import com.binas.yak.data.model.translation.Translation
import com.binas.yak.ui.base.view.BaseActivity
import com.binas.yak.ui.main.view.MainActivity
import com.binas.yak.ui.settings.view.SettingsActivity
import com.binas.yak.ui.study.common.breakActivity.BreakActivity
import com.binas.yak.ui.study.sign.learn.studyCard.interactor.SignStudyCardInteractor
import com.binas.yak.ui.study.sign.learn.studyCard.presenter.SignStudyCardPresenter
import com.binas.yak.ui.study.sign.learn.writing.view.LearnSignWritingActivity
import com.yariksoffice.lingver.Lingver
import kotlinx.android.synthetic.main.activity_sign_study_card.*
import kotlinx.android.synthetic.main.fragment_action_bar_with_timer.*
import kotlinx.android.synthetic.main.fragment_animation.*
import javax.inject.Inject

open class SignStudyCardActivity : BaseActivity(), SignStudyCardView {

    private var playing: Boolean = false
    private var imgName: String = ""
    private var soundName: String = ""
    private var card: SignStudyFlashcard? = null
    private var sign: Sign? = null
    private var translation: Translation? = null
    private var id: Long? = null
    private var timeLeft = 0L
    private var timeStarted = 0L
    @Inject
    lateinit var presenter: SignStudyCardPresenter<SignStudyCardView, SignStudyCardInteractor>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_study_card)
        timeLeft = intent.getLongExtra("time", 0L)
        startTimer()
        presenter.onAttach(this)
        presenter?.start(intent.getLongExtra("id", -1))
        timeStarted = SystemClock.elapsedRealtime()
    }

    override fun loadAnimation() {
        animationFragment.animationView.setAnimation("animations/" + this.imgName + ".json")
        animationFragment.animationView.repeatCount = LottieDrawable.INFINITE
        animationFragment.animationView.speed = 1f
        animationFragment.animationView.playAnimation()
    }

    override fun clickPlaySound() {
        playSoundButton.callOnClick()
    }

    fun onClickGoToLearnNewSign(view: View) {
        timeLeft = fragment.timer.base
        val intent: Intent = Intent(this, LearnSignWritingActivity::class.java)
        intent.putExtra("imageName", imgName)
        intent.putExtra("signId", id)
        intent.putExtra("time", timeLeft)
        intent.putExtra("timeStarted", timeStarted)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }

    fun onClickSettingsButton(view: View) {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }

    fun onClickBackButton(view: View) {
        this.onBackPressed()
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_out_bottom)
        finish()
    }

    fun onClickPlaySound(view: View) {
        var sound = resources.getIdentifier(soundName, "raw", packageName)
        if (!playing && sound != null) {
            playing = true
            val mp = MediaPlayer()
            val uri = Uri.parse("android.resource://$packageName/$sound")
            mp.setDataSource(this, uri)
            mp.prepare()
            mp.setOnPreparedListener { mp.start() }
            mp.setOnCompletionListener { playing = false }
        }
    }

    override fun setContent(card: SignStudyFlashcard?, sign: Sign?, translation: Translation?) {
        signText.text = sign!!.tibetanSign + " (" + sign!!.audioFileName + ")"
        if(Lingver.getInstance().getLanguage() == "pl") {
            description.text = translation?.polish
        } else {
            description.text = translation?.english
        }
        imgName = sign!!.audioFileName.toString()
        soundName = sign!!.audioFileName.toString()
        id = sign.id
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
}