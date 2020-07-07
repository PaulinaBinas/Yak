package com.binas.yak.ui.settings.changeLimit.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.binas.yak.R
import com.binas.yak.ui.base.view.BaseActivity
import com.binas.yak.ui.settings.changeLimit.interactor.ChangeLimitInteractor
import com.binas.yak.ui.settings.changeLimit.presenter.ChangeLimitPresenter
import com.binas.yak.ui.settings.view.SettingsActivity
import kotlinx.android.synthetic.main.activity_change_limit.*
import javax.inject.Inject

class ChangeLimitActivity : BaseActivity(), ChangeLimitView {

    @Inject
    lateinit var presenter: ChangeLimitPresenter<ChangeLimitView, ChangeLimitInteractor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_limit)
        presenter?.onAttach(this)
        presenter?.start()
    }

    override fun setNumberOfCards(number: Int) {
        var i = editTextNumber
        editTextNumber.setText(number.toString())
    }

    fun saveNewLimit(view: View) {
        var newNumber = editTextNumber.text.toString().toInt()
        presenter?.changeDailyCardsLimit(newNumber)
        onBackPressed()
    }

    fun onClickSettingsButton(view: View) {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }

    fun onClickBackButton(view: View) {
        onBackPressed()
    }
}