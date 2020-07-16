package com.binas.yak.ui.study.common.meaningCheck.view

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.SystemClock
import android.view.MotionEvent
import android.view.View
import com.binas.yak.R
import com.binas.yak.ui.base.view.BaseActivity
import com.binas.yak.ui.settings.view.SettingsActivity
import com.binas.yak.ui.study.common.breakActivity.BreakActivity
import com.binas.yak.ui.study.common.meaningCheck.interactor.MeaningCheckInteractor
import com.binas.yak.ui.study.common.meaningCheck.presenter.MeaningCheckPresenter
import com.binas.yak.ui.study.view.StudyActivity
import com.bumptech.glide.Glide
import com.yariksoffice.lingver.Lingver
import kotlinx.android.synthetic.main.activity_meaning_check.*
import kotlinx.android.synthetic.main.fragment_action_bar_with_timer.*
import kotlinx.android.synthetic.main.fragment_image.*
import javax.inject.Inject


class MeaningCheckActivity : BaseActivity(), MeaningCheckView {

    @Inject
    lateinit var presenter: MeaningCheckPresenter<MeaningCheckView, MeaningCheckInteractor>
    private var playing: Boolean = false
    private var soundName: String = ""
    private var imageName: String = ""
    private var tibetanWord: String = ""
    private var translation: String = ""
    private var id: Long? = null
    private var timeStarted = 0L
    private var timeLeft = 0L
    private var timeEnded = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meaning_check)
        presenter?.onAttach(this)
        this.setContent()
        loadImage()
        timeLeft = intent.getLongExtra("time", 0L)
        startTimer()
        timeStarted = intent.getLongExtra("timeStarted", SystemClock.elapsedRealtime())
    }

    private fun setContent() {
        this.imageName = intent.getStringExtra("imageFileName").toString()
        this.soundName = intent.getStringExtra("audioFileName").toString()
        this.tibetanWord = intent.getStringExtra("word").toString()
        this.id = intent.getLongExtra("id", -1L)
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

    fun onClickCorrect(view: View) {
        timeEnded = SystemClock.elapsedRealtime()
        timeLeft = fragment.timer.base
        this.id?.let { this.presenter?.reviseCard(it, true) }
        goToStudy()
    }

    fun onClickIncorrect(view: View) {
        timeEnded = SystemClock.elapsedRealtime()
        timeLeft = fragment.timer.base
        this.id?.let { this.presenter?.reviseCard(it, false) }
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