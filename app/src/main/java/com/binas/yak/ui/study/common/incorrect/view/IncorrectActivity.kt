package com.binas.yak.ui.study.common.incorrect.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.airbnb.lottie.LottieDrawable
import com.binas.yak.R
import com.binas.yak.ui.base.view.BaseActivity
import com.binas.yak.ui.study.view.StudyActivity
import kotlinx.android.synthetic.main.activity_incorrect.*

class IncorrectActivity : BaseActivity(), IncorrectView {

    private val SCREEN_TIME: Long = 1000
    private var imageFileName = "yak"
    var timeLeft = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incorrect)
        timeLeft = intent.getLongExtra("time", 0L)
        loadAnimation()
        Handler().postDelayed({
            goToStudy()
        }, SCREEN_TIME)
    }

    private fun goToStudy() {
        val intent = Intent(this, StudyActivity::class.java)
        intent.putExtra("time", timeLeft)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }

    fun loadAnimation() {
        animation.setAnimation("animations/" + this.imageFileName + ".json")
        animation.repeatCount = LottieDrawable.INFINITE
        animation.speed = 1f
        animation.playAnimation()
    }
}