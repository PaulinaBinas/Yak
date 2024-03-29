package com.binas.yak.ui.studiedElements.calendar.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.airbnb.lottie.LottieDrawable
import com.binas.yak.R
import com.binas.yak.ui.base.view.BaseActivity
import com.binas.yak.ui.base.view.BaseView
import com.binas.yak.ui.settings.view.SettingsActivity
import kotlinx.android.synthetic.main.activity_calendar.*

class CalendarActivity : BaseActivity(), BaseView {

    private var animationFileName = "yak"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)
        var days = intent.getIntExtra("days", 0)
        if(days < 0) days = 0
        var dayWord = if(days == 1) getString(R.string.one_day) else getString(R.string.days)
        revise.text = revise.text.toString() + " " + days + " " + dayWord
        loadAnimation()
    }

    fun onClickSettingsButton(view: View) {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }

    fun onClickBackButton(view: View) {
        onBackPressed()
    }

    private fun loadAnimation() {
        animation.setAnimation("animations/" + this.animationFileName + ".json")
        animation.repeatCount = LottieDrawable.INFINITE
        animation.speed = 1f
        animation.playAnimation()
    }
}