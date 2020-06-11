package com.binas.yak.ui.settings

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.binas.yak.R
import com.binas.yak.ui.authentication.AuthMenuActivity
import com.binas.yak.ui.main.MainActivity
import com.google.firebase.auth.FirebaseAuth
import java.util.*

class SettingsActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        mAuth = FirebaseAuth.getInstance()
    }

    fun onClickSettingsButton(view: View) {
    }

    fun onClickLogoutButton(view: View) {
        mAuth.signOut()
        val intent = Intent(this, AuthMenuActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
        finish()
    }

    fun onClickBackButton(view: View) {
        onBackPressed()
    }

    fun onClickChangeLanguage(view: View) {
        val intent = Intent(this, ChangeLanguageActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }

    fun onClickGoToChangePassword(view: View) {
        val intent = Intent(this, ChangePasswordActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }
}
