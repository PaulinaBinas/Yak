package com.binas.yak.ui.authentication.resetPassword.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.binas.yak.R
import com.binas.yak.ui.authentication.login.view.LoginActivity
import com.binas.yak.ui.base.view.BaseActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_reset_password.*

class ResetPasswordActivity : BaseActivity(), ResetPasswordView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)
    }

    fun onClickResetPassword(view: View) {
        var email = emailEditText.text
        var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.sendPasswordResetEmail(email.toString())
        Toast.makeText(baseContext, resources.getString(R.string.passwordReset),
            Toast.LENGTH_SHORT).show()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }
}
