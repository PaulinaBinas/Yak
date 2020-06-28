package com.binas.yak.ui.authentication.resetPassword.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.binas.yak.R
import com.binas.yak.ui.authentication.login.view.LoginActivity
import com.binas.yak.ui.base.view.BaseActivity

class ResetPasswordActivity : BaseActivity(), ResetPasswordView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)
    }

    fun onClickResetPassword(view: View) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }
}
