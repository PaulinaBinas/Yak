package com.binas.yak.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import com.airbnb.lottie.LottieDrawable
import com.binas.yak.R
import com.binas.yak.ui.achievements.view.AchievementsActivity
import com.binas.yak.ui.authentication.signup.view.SignupActivity
import com.binas.yak.ui.base.view.BaseActivity
import com.binas.yak.ui.settings.view.SettingsActivity
import com.binas.yak.ui.studiedElements.view.StudiedElementsActivity
import com.binas.yak.ui.study.view.StudyActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_incorrect.*


class MainActivity : BaseActivity(), MainView {

    private lateinit var mAuth: FirebaseAuth
    private var animationFileName = "yak"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAuth = FirebaseAuth.getInstance()
        loadAnimation()
    }

    override fun onStart() {
        super.onStart()
        val currentUser = mAuth.currentUser
        updateUI(currentUser)
    }

    fun updateUI(user: FirebaseUser?) {
        if(user == null) {
            val intent =  Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }

    fun onClickGoToStudy(view: View) {
        val intent = Intent(this, StudyActivity::class.java)
        intent.putExtra("time", SystemClock.elapsedRealtime() + (60*25*1000L))
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }

    fun onClickGoToStudiedElements(view: View) {
        val intent = Intent(this, StudiedElementsActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }

    fun onClickGoToAchievements(view: View) {
        val intent = Intent(this, AchievementsActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }

    fun onClickSettingsButton(view: View) {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }

    private fun loadAnimation() {
        animation.setAnimation("animations/" + this.animationFileName + ".json")
        animation.repeatCount = LottieDrawable.INFINITE
        animation.speed = 1f
        animation.playAnimation()
    }

    override fun onBackPressed() {}
}
