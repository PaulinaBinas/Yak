package com.binas.yak.ui.settings

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.binas.yak.R
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_change_password.*


class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)
        mAuth = FirebaseAuth.getInstance()
    }

    fun onClickChangePassword(view: View) {
        val email = mAuth.currentUser?.email ?: ""
        val password = oldPassword.text.toString()
        val credential = EmailAuthProvider.getCredential(email, password)
        val newPass = newPassword.text.toString()
        val repeatPass = newPasswordRepeat.text.toString()

        if(repeatPass.equals(newPass)) {
            mAuth.currentUser?.reauthenticate(credential)
                ?.addOnCompleteListener(this) { task ->
                    if (task.isSuccessful()) {
                        mAuth.currentUser?.updatePassword(newPass)
                        val intent = Intent(this, SettingsActivity::class.java)
                        startActivity(intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
                        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
                        finish()
                    } else {
                        Toast.makeText(applicationContext,"Password change failed!\nTry again",Toast.LENGTH_SHORT).show()
                        oldPassword.text.clear()
                        newPassword.text.clear()
                        newPasswordRepeat.text.clear()
                    }
                }
        }
    }
}
