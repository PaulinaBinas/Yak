package com.binas.yak.ui.main.splash.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.airbnb.lottie.LottieDrawable
import com.binas.yak.R
import com.binas.yak.ui.authentication.authMenu.view.AuthMenuActivity
import com.binas.yak.ui.base.view.BaseActivity
import com.binas.yak.ui.main.view.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity(), SplashView {

    private val SPLASH_SCREEN_TIME: Long = 2000;

    private lateinit var mAuth: FirebaseAuth
    private val imgName = "yak"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        loadAnimation()
        Handler().postDelayed({
            mAuth = FirebaseAuth.getInstance()
            val user = mAuth.currentUser

            updateUi(user)
            finish()
        }, SPLASH_SCREEN_TIME)
    }

    private fun updateUi(user: FirebaseUser?) {
        if(user != null) {
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            startActivity(Intent(this, AuthMenuActivity::class.java))
        }
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
    }

    private fun loadAnimation() {
        animationView.setAnimation("animations/$imgName.json")
        animationView.repeatCount = LottieDrawable.INFINITE
        animationView.speed = 1f
        animationView.playAnimation()
    }
}
