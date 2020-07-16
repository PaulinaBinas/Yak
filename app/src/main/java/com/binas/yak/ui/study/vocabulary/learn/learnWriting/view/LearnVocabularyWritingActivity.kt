package com.binas.yak.ui.study.vocabulary.learn.learnWriting.view

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import com.binas.yak.R
import com.binas.yak.ui.base.view.BaseActivity
import com.binas.yak.ui.others.drawing.view.DrawingFragment
import com.binas.yak.ui.settings.view.SettingsActivity
import com.binas.yak.ui.study.common.breakActivity.BreakActivity
import com.binas.yak.ui.study.view.StudyActivity
import com.binas.yak.ui.study.vocabulary.learn.learnWriting.interactor.LearnVocabularyWritingInteractor
import com.binas.yak.ui.study.vocabulary.learn.learnWriting.presenter.LearnVocabularyWritingPresenter
import kotlinx.android.synthetic.main.activity_learn_vocabulary_writing.*
import kotlinx.android.synthetic.main.fragment_action_bar_with_timer.*
import javax.inject.Inject

class LearnVocabularyWritingActivity : BaseActivity(), LearnVocabularyWritingView {

    @Inject
    lateinit var presenter: LearnVocabularyWritingPresenter<LearnVocabularyWritingView, LearnVocabularyWritingInteractor>
    private var id: Long? = null
    private var timeStarted = 0L
    private var timeLeft = 0L
    private var timeEnded = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn_vocabulary_writing)
        vocabularyTextView.text = intent.getStringExtra("word")
        timeLeft = intent.getLongExtra("time", 0L)
        presenter.onAttach(this)
        id = intent.getLongExtra("id", -1L)
        startTimer()
        timeStarted = intent.getLongExtra("timeStarted", SystemClock.elapsedRealtime())
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
        timeEnded = SystemClock.elapsedRealtime()
        timeLeft = fragment.timer.base
        id?.let {
            presenter?.markCardAsStudied(it)
            presenter?.scheduleReviewCards(it)
        }
        val intent = Intent(this, StudyActivity::class.java)
        intent.putExtra("time", timeLeft)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
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

    override fun getTimeLeft(): Long = this.timeLeft

    override fun getDuration(): Long {
        return timeEnded - timeStarted
    }
}