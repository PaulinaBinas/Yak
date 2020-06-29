package com.binas.yak.ui.base.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjection

abstract class BaseFragment: Fragment(), BaseView {

    private var containingActivity: BaseActivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        this.injectDependencies()
        super.onCreate(savedInstanceState)
    }

    private fun injectDependencies() = AndroidInjection.inject(this.activity)

    fun getBaseActivity() = containingActivity
}