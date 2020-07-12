package com.binas.yak.ui.authentication.signup.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.binas.yak.R
import com.binas.yak.data.model.user.User
import com.binas.yak.ui.authentication.signup.interactor.SignupInteractor
import com.binas.yak.ui.authentication.signup.presenter.SignupPresenter
import com.binas.yak.ui.base.view.BaseActivity
import com.binas.yak.ui.main.view.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_signup.*
import javax.inject.Inject

class SignupActivity : BaseActivity(), SignupView {

    @Inject
    lateinit var presenter: SignupPresenter<SignupView, SignupInteractor>
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        mAuth = FirebaseAuth.getInstance()
        presenter?.onAttach(this)
    }

    fun signUpNewUser(view: View) {
        var email = emailEditText.text.toString()
        var password = passwordEditText.text.toString()
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = mAuth.currentUser
                    presenter?.addNewUser(email)
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
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
}
