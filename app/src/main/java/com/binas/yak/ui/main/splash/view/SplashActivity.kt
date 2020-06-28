package com.binas.yak.ui.main.splash.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.binas.yak.R
import com.binas.yak.ui.authentication.authMenu.view.AuthMenuActivity
import com.binas.yak.ui.base.view.BaseActivity
import com.binas.yak.ui.main.view.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SplashActivity : BaseActivity(), SplashView {

    private val SPLASH_SCREEN_TIME: Long = 1500;

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity
            mAuth = FirebaseAuth.getInstance()
            val user = mAuth.currentUser

            updateUi(user)
            // close this activity
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
}
