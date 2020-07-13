package com.binas.yak.ui.authentication.signup.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.airbnb.lottie.LottieDrawable
import com.binas.yak.R
import com.binas.yak.ui.authentication.signup.interactor.SignupInteractor
import com.binas.yak.ui.authentication.signup.presenter.SignupPresenter
import com.binas.yak.ui.base.view.BaseActivity
import com.binas.yak.ui.main.view.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_signup.*
import javax.inject.Inject

class SignupActivity : BaseActivity(), SignupView {

    @Inject
    lateinit var presenter: SignupPresenter<SignupView, SignupInteractor>
    private lateinit var mAuth: FirebaseAuth
    private var imgName = "yak"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        mAuth = FirebaseAuth.getInstance()
        presenter?.onAttach(this)
    }

    fun signUpNewUser(view: View) {
        var email = emailEditText.text.toString()
        var password = passwordEditText.text.toString()
        loadAnimation()
        signupButton.isClickable = false
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = mAuth.currentUser
                    presenter?.addNewUser(email)
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    signupButton.isClickable = true
                    Toast.makeText(baseContext, resources.getString(R.string.registerFail),
                        Toast.LENGTH_LONG).show()
                    updateUI(null)
                }

                // ...
            }
    }

    fun updateUI(user: FirebaseUser?) {
        if(user != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
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
