package com.binas.yak.ui.study.common.compareWriting.view

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.os.SystemClock
import android.text.SpannableStringBuilder
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import androidx.core.text.color
import com.airbnb.lottie.LottieDrawable
import com.binas.yak.R
import com.binas.yak.ui.base.view.BaseActivity
import com.binas.yak.ui.settings.view.SettingsActivity
import com.binas.yak.ui.study.common.breakActivity.BreakActivity
import com.binas.yak.ui.study.common.compareWriting.interactor.CompareWritingInteractor
import com.binas.yak.ui.study.common.compareWriting.presenter.CompareWritingPresenter
import com.binas.yak.ui.study.view.StudyActivity
import kotlinx.android.synthetic.main.activity_compare_writing.*
import kotlinx.android.synthetic.main.fragment_action_bar_with_timer.*
import kotlinx.android.synthetic.main.fragment_animation.*
import kotlinx.android.synthetic.main.fragment_image.*
import javax.inject.Inject


class CompareWritingActivity : BaseActivity(), CompareWritingView {

    @Inject
    lateinit var presenter: CompareWritingPresenter<CompareWritingView, CompareWritingInteractor>
    private var sentenceStart: String = ""
    private var sentenceEnd: String = ""
    private var grammar: String = ""
    private var imageName: String = ""
    private var word: String = ""
    private var type: String = ""
    private var id: Long? = null
    private var timeLeft = 0L
    private var timeStarted = 0L
    private var timeEnded = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compare_writing)
        presenter?.onAttach(this)
        timeLeft = intent.getLongExtra("time", 0L)
        timeStarted = intent.getLongExtra("timeStarted", SystemClock.elapsedRealtime())
        startTimer()
    }

    override fun onStart() {
        super.onStart()
        val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(MATCH_PARENT, 0,
            1.4F
        )
        if(intent.hasExtra("image")) {
            imageName = intent.getStringExtra("image")
            imageLinearLayout.layoutParams = params
            loadAnimation()
        } else if(intent.hasExtra("sentenceStart") && intent.hasExtra("grammar") && intent.hasExtra("sentenceEnd")) {
            sentenceStart = intent.getStringExtra("sentenceStart")
            grammar = intent.getStringExtra("grammar")
            sentenceEnd = intent.getStringExtra("sentenceEnd")
            sentenceLinearLayout.layoutParams = params
            loadGrammarText()
        } else if(intent.hasExtra("word")) {
            word = intent.getStringExtra("word")
            sentenceLinearLayout.layoutParams = params
            sentenceTextView.text = word
        }
        this.type = intent.getStringExtra("type")
        this.id = intent.getLongExtra("id", -1L)
        val bytes = intent.getByteArrayExtra("bitmap")
        val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
        imageFragment.imageView.setImageBitmap(bitmap)
    }

    private fun loadAnimation() {
        animationFragment.animationView.setAnimation("animations/" + this.imageName + ".json")
        animationFragment.animationView.repeatCount = LottieDrawable.INFINITE
        animationFragment.animationView.speed = 1f
        animationFragment.animationView.playAnimation()
    }

    private fun loadGrammarText() {
        var text = SpannableStringBuilder()
            .append(sentenceStart)
            .color(Color.rgb(100, 171, 113)) { append(grammar) }
            .append(sentenceEnd)
        sentenceTextView.text = text
    }

    fun onClickSettingsButton(view: View) {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }

    fun onClickBackButton(view: View) {
        onBackPressed()
    }

    fun onClickCorrect(view: View) {
        timeEnded = SystemClock.elapsedRealtime()
        timeLeft = fragment.timer.base
        this.id?.let { this.presenter?.reviseCard(this.type, it, true) }
        goToStudy()
    }
    fun onClickIncorrect(view: View) {
        timeEnded = SystemClock.elapsedRealtime()
        timeLeft = fragment.timer.base
        this.id?.let { this.presenter?.reviseCard(this.type, it, false) }
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