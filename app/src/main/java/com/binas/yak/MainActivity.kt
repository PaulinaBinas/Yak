package com.binas.yak

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.binas.yak.authentication.LoginActivity
import com.binas.yak.authentication.SignupActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


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
            var greetingText = findViewById<TextView>(R.id.greetingTextView)
            var uidText = findViewById<TextView>(R.id.uidTextView)
            greetingText.setText(greetingText.text.toString() + user.email + "!")
            uidText.setText(user.uid)
        }

    }

    fun onClickLogoutButton(view: View) {
        mAuth.signOut()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}
