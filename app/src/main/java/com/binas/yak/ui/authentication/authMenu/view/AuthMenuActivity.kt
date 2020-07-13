package com.binas.yak.ui.authentication.authMenu.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.airbnb.lottie.LottieDrawable
import com.binas.yak.R
import com.binas.yak.ui.authentication.login.view.LoginActivity
import com.binas.yak.ui.authentication.signup.view.SignupActivity
import com.binas.yak.ui.base.view.BaseActivity
import kotlinx.android.synthetic.main.activity_incorrect.*

class AuthMenuActivity : BaseActivity(), AuthMenuView {

    private var animationFileName = "yak"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth_menu)
        loadAnimation()
    }

    fun onClickLoginButtonInMenu(view: View) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
    }

    fun onClickSignUpButtonInMenu(view: View) {
        val intent = Intent(this, SignupActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
    }

    private fun loadAnimation() {
        animation.setAnimation("animations/" + this.animationFileName + ".json")
        animation.repeatCount = LottieDrawable.INFINITE
        animation.speed = 1f
        animation.playAnimation()
    }
}
