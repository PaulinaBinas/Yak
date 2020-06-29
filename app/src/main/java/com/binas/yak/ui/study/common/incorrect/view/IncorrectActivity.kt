package com.binas.yak.ui.study.common.incorrect.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.binas.yak.R
import com.binas.yak.ui.base.view.BaseActivity

class IncorrectActivity : BaseActivity(), IncorrectView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incorrect)
    }
}