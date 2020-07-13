package com.binas.yak.ui.authentication.login.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.airbnb.lottie.LottieDrawable
import com.binas.yak.R
import com.binas.yak.ui.authentication.login.interactor.LoginInteractor
import com.binas.yak.ui.authentication.login.presenter.LoginPresenter
import com.binas.yak.ui.authentication.resetPassword.view.ResetPasswordActivity
import com.binas.yak.ui.base.view.BaseActivity
import com.binas.yak.ui.main.view.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : BaseActivity(), LoginView {

    @Inject
    lateinit var presenter: LoginPresenter<LoginView, LoginInteractor>

    private lateinit var mAuth: FirebaseAuth
    private var imgName = "yak"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mAuth = FirebaseAuth.getInstance()
    }

    fun onClickLoginButton(view: View) {
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()
        if(email != null && password != null) {
            signupButton.isClickable = false
            loadAnimation()
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = mAuth.currentUser
                        if (user != null) {
                            presenter.setCurrentUser(user.email!!)
                        }
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        stopAnimation()
                        signupButton.isClickable = true
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

    private fun updateUI(user: FirebaseUser?) {
        if(user != null) {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
            finish()
        }
    }

    private fun loadAnimation() {
        loadingAnimation.setAnimation("animations/$imgName.json")
        loadingAnimation.repeatCount = LottieDrawable.INFINITE
        loadingAnimation.speed = 1f
        loadingAnimation.visibility = View.VISIBLE
        loadingAnimation.playAnimation()
    }

    private fun stopAnimation() {
        loadingAnimation.cancelAnimation()
        loadingAnimation.visibility = View.INVISIBLE
    }
}
