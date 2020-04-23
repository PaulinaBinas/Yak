package com.binas.yak.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.binas.yak.R
import com.binas.yak.ui.login.LoginActivity
import com.binas.yak.ui.signup.SignupActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*;


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
    }

    fun updateUI(user: FirebaseUser?) {
        if(user == null) {
            val intent =  Intent(this, SignupActivity::class.java)
            startActivity(intent)
        } else {
            greetingTextView.setText(greetingTextView.text.toString() + user.email + "!")
            uidTextView.setText(user.uid)
        }

    }

    fun onClickLogoutButton(view: View) {
        mAuth.signOut()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}
