package com.binas.yak.ui.study.vocabulary.reviseWriting.view

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import com.binas.yak.R
import com.binas.yak.data.model.vocabulary.Vocabulary
import com.binas.yak.data.model.vocabulary.VocabularyRevisionFlashcard
import com.binas.yak.ui.base.view.BaseActivity
import com.binas.yak.ui.main.view.MainActivity
import com.binas.yak.ui.settings.view.SettingsActivity
import com.binas.yak.ui.study.common.breakActivity.BreakActivity
import com.binas.yak.ui.study.common.reviseWriting.view.ReviseWritingActivity
import com.binas.yak.ui.study.vocabulary.reviseWriting.interactor.VocabularyReviseWritingInteractor
import com.binas.yak.ui.study.vocabulary.reviseWriting.presenter.VocabularyReviseWritingPresenter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_vocabulary_revise_writing.*
import kotlinx.android.synthetic.main.fragment_action_bar_with_timer.*
import kotlinx.android.synthetic.main.fragment_image.*
import javax.inject.Inject

open class VocabularyReviseWritingActivity : BaseActivity(), VocabularyReviseWritingView {

    @Inject
    lateinit var presenter: VocabularyReviseWritingPresenter<VocabularyReviseWritingView, VocabularyReviseWritingInteractor>
    private var playing: Boolean = false
    private var soundName: String = ""
    private var imageName: String = ""
    private var id: Long? = null
    private var word: String = ""
    private var timeLeft = 0L
    private var timeStarted = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vocabulary_revise_writing)
        id = intent.getLongExtra("id", -1L)
        timeLeft = intent.getLongExtra("time", 0L)
        presenter?.onAttach(this)
        presenter?.start(id!!)
        startTimer()
        timeStarted = SystemClock.elapsedRealtime()
    }

    override fun onStart() {
        super.onStart()
        playSoundButton.callOnClick()
    }

    override fun loadImage() {
        Glide.with(baseContext)
            .load(resources.getIdentifier(imageName, "drawable", this.packageName))
            .into(imageFragment.imageView)
    }

    override fun setContent(card: VocabularyRevisionFlashcard, vocabulary: Vocabulary) {
        this.soundName = vocabulary.audioFileName.toString()
        this.imageName = this.soundName
        this.word = vocabulary.tibetanWord.toString()
        this.id = card.id
    }

    fun onClickGoToReviseWriting(view: View) {
        timeLeft = fragment.timer.base
        val intent = Intent(this, ReviseWritingActivity::class.java)
        intent.putExtra("word", word)
        intent.putExtra("id", id)
        intent.putExtra("type", "vocabulary")
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
        if (!playing) {
            playing = true
            val mp: MediaPlayer = MediaPlayer()
            val uri = Uri.parse("android.resource://$packageName/$sound")
            mp.setDataSource(this, uri)
            mp.prepare()
            mp.setOnPreparedListener { mp.start() }
            mp.setOnCompletionListener { playing = false }
        }
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