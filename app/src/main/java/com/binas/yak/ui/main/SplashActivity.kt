package com.binas.yak.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.binas.yak.R
import com.binas.yak.ui.authentication.AuthMenu

class SplashActivity : AppCompatActivity() {

    private val SPLASH_SCREEN_TIME: Long = 3000;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity

            startActivity(Intent(this, AuthMenu::class.java))
            overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
            // close this activity
            finish()
        }, SPLASH_SCREEN_TIME)
    }
}
