package com.binas.yak.ui.authentication.login.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.binas.yak.ui.main.view.MainActivity
import com.binas.yak.R
import com.binas.yak.ui.authentication.resetPassword.view.ResetPasswordActivity
import com.binas.yak.ui.base.view.BaseActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), LoginView {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mAuth = FirebaseAuth.getInstance()
    }

    fun onClickLoginButton(view: View) {
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()
        if(email != null && password != null) {
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = mAuth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                        updateUI(null)
                        // ...
                    }

                    // ...
                }
        }
    }

    fun onClickGoToResetPassword(view: View) {
        val intent = Intent(this, ResetPasswordActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }

    fun updateUI(user: FirebaseUser?) {
        if(user != null) {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
            finish()
        }
    }
}
