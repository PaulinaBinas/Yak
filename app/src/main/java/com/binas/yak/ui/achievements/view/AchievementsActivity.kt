package com.binas.yak.ui.achievements.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.binas.yak.R
import com.binas.yak.ui.achievements.interactor.AchievementsInteractor
import com.binas.yak.ui.achievements.presenter.AchievementsPresenter
import com.binas.yak.ui.base.view.BaseActivity
import com.binas.yak.ui.settings.view.SettingsActivity
import kotlinx.android.synthetic.main.activity_achievements.*
import javax.inject.Inject

class AchievementsActivity : BaseActivity(), AchievementsView {

    @Inject
    lateinit var presenter: AchievementsPresenter<AchievementsView, AchievementsInteractor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_achievements)
        presenter?.onAttach(this)
        presenter?.start()
    }

    fun onClickSettingsButton(view: View) {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }

    fun onClickBackButton(view: View) {
        onBackPressed()
    }

    override fun setContent(
        signsStudied: Int,
        signsNo: Int,
        wordsStudied: Int,
        wordsNo: Int,
        grammarStudied: Int,
        grammarNo: Int
    ) {
        signsCount.text = signsCount.text.toString() + " " + signsStudied + "/" + signsNo
        wordsCount.text = wordsCount.text.toString() + " " + wordsStudied + "/" + wordsNo
        grammarCount.text = grammarCount.text.toString() + " " + grammarStudied + "/" + grammarNo
    }
}