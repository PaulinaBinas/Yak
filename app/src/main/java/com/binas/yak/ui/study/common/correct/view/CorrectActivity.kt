package com.binas.yak.ui.study.common.correct.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.binas.yak.R
import com.binas.yak.ui.base.view.BaseActivity

class CorrectActivity : BaseActivity(), CorrectView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_correct)
    }
}