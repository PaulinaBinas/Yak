package com.binas.yak.ui.study.vocabulary.reviseMeaning.view

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import com.binas.yak.R
import com.binas.yak.data.model.translation.Translation
import com.binas.yak.data.model.vocabulary.Vocabulary
import com.binas.yak.data.model.vocabulary.VocabularyRevisionFlashcard
import com.binas.yak.ui.base.view.BaseActivity
import com.binas.yak.ui.main.view.MainActivity
import com.binas.yak.ui.settings.view.SettingsActivity
import com.binas.yak.ui.study.common.breakActivity.BreakActivity
import com.binas.yak.ui.study.common.meaningCheck.view.MeaningCheckActivity
import com.binas.yak.ui.study.vocabulary.reviseMeaning.interactor.VocabularyReviseMeaningInteractor
import com.binas.yak.ui.study.vocabulary.reviseMeaning.presenter.VocabularyReviseMeaningPresenter
import kotlinx.android.synthetic.main.activity_vocabulary_revise_meaning.*
import kotlinx.android.synthetic.main.fragment_action_bar_with_timer.*
import javax.inject.Inject

open class VocabularyReviseMeaningActivity : BaseActivity(), VocabularyReviseMeaningView {

    private var playing: Boolean = false
    private var soundName: String = ""
    private var imageFileName: String = ""
    private var tibetanWord: String = ""
    private var polish: String = ""
    private var english: String = ""
    private var id: Long? = null
    private var timeLeft = 0L
    private var timeStarted = 0L
    @Inject
    lateinit var presenter: VocabularyReviseMeaningPresenter<VocabularyReviseMeaningView, VocabularyReviseMeaningInteractor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vocabulary_revise_meaning)
        id = intent.getLongExtra("id", -1L)
        timeLeft = intent.getLongExtra("time", 0L)
        presenter?.onAttach(this)
        presenter?.start(id!!)
        startTimer()
        timeStarted = SystemClock.elapsedRealtime()
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

    fun onClickGoToMeaningCheck(view: View) {
        timeLeft = fragment.timer.base
        val intent = Intent(this, MeaningCheckActivity::class.java)
        intent.putExtra("imageFileName", this.imageFileName)
        intent.putExtra("word", this.tibetanWord)
        intent.putExtra("audioFileName", this.imageFileName)
        intent.putExtra("polish", this.polish)
        intent.putExtra("english", this.english)
        intent.putExtra("id", this.id)
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

    override fun setContent(card: VocabularyRevisionFlashcard, word: Vocabulary, translation: Translation?) {
        this.soundName = word.audioFileName.toString()
        this.imageFileName = soundName
        this.tibetanWord = word.tibetanWord.toString()
        this.polish = translation?.polish.toString()
        this.english = translation?.english.toString()
        this.id = card.id
    }

    override fun clickSoundButton() {
        playSoundButton.callOnClick()
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