package com.binas.yak.ui.study.common.correct.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.binas.yak.R
import com.binas.yak.ui.base.view.BaseActivity
import com.binas.yak.ui.study.common.correct.interactor.CorrectInteractor
import com.binas.yak.ui.study.common.correct.presenter.CorrectPresenter
import com.binas.yak.ui.study.view.StudyActivity
import javax.inject.Inject

class CorrectActivity : BaseActivity(), CorrectView {

    private val SCREEN_TIME: Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_correct)
        Handler().postDelayed({
            goToStudy()
        }, SCREEN_TIME)
    }

    private fun goToStudy() {
        val intent = Intent(this, StudyActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }
}