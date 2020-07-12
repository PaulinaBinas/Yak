package com.binas.yak.ui.settings.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.binas.yak.R
import com.binas.yak.ui.authentication.authMenu.view.AuthMenuActivity
import com.binas.yak.ui.base.view.BaseActivity
import com.binas.yak.ui.settings.changeLanguage.view.ChangeLanguageActivity
import com.binas.yak.ui.settings.changeLimit.view.ChangeLimitActivity
import com.binas.yak.ui.settings.changePassword.view.ChangePasswordActivity
import com.binas.yak.ui.settings.interactor.SettingsInteractor
import com.binas.yak.ui.settings.presenter.SettingsPresenter
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class SettingsActivity : BaseActivity(), SettingsView {

    @Inject
    lateinit var presenter: SettingsPresenter<SettingsView, SettingsInteractor>
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        mAuth = FirebaseAuth.getInstance()
        presenter?.onAttach(this)
    }

    fun onClickSettingsButton(view: View) {
    }

    fun onClickLogoutButton(view: View) {
        mAuth.signOut()
        presenter?.logoutUser()
        val intent = Intent(this, AuthMenuActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
        finish()
    }

    fun onClickBackButton(view: View) {
        onBackPressed()
    }

    fun onClickChangeLanguage(view: View) {
        val intent = Intent(this, ChangeLanguageActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }

    fun onClickGoToChangePassword(view: View) {
        val intent = Intent(this, ChangePasswordActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }

    fun onClickChangeLimit(view: View) {
        val intent = Intent(this, ChangeLimitActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }
}
