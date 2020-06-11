package com.binas.yak.ui.main

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.binas.yak.R
import com.binas.yak.ui.authentication.SignupActivity
import com.binas.yak.ui.study.LearnNewSignActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAuth = FirebaseAuth.getInstance()
    }

    override fun onStart() {
        super.onStart()
        val currentUser = mAuth.currentUser
        updateUI(currentUser)
        println("${baseContext.resources.configuration.locale.language }       !!!!!!!!!!!!")
    }

    fun updateUI(user: FirebaseUser?) {
        if(user == null) {
            val intent =  Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }

    fun onClickGoToStudy(view: View) {
        val intent = Intent(this, LearnNewSignActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }
}
