package com.binas.yak.ui.study.sign.reviseWriting.view

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.binas.yak.R
import com.binas.yak.data.model.sign.Sign
import com.binas.yak.data.model.sign.SignRevisionFlashcard
import com.binas.yak.ui.base.view.BaseActivity
import com.binas.yak.ui.settings.view.SettingsActivity
import com.binas.yak.ui.study.common.reviseWriting.view.ReviseWritingActivity
import com.binas.yak.ui.study.sign.reviseWriting.interactor.SignReviseWritingInteractor
import com.binas.yak.ui.study.sign.reviseWriting.presenter.SignReviseWritingPresenter
import kotlinx.android.synthetic.main.activity_vocabulary_revise_writing.*
import javax.inject.Inject

class SignReviseWritingActivity : BaseActivity(), SignReviseWritingView {

    var playing: Boolean = false
    var imageName: String = ""
    var audioName: String = ""
    @Inject
    lateinit var presenter: SignReviseWritingPresenter<SignReviseWritingView, SignReviseWritingInteractor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_revise_writing)
        presenter?.onAttach(this)
        presenter?.start()
    }

    fun onClickGoToReviseWriting(view: View) {
        val intent = Intent(this, ReviseWritingActivity::class.java)
        intent.putExtra("image", imageName)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }

    fun onClickSettingsButton(view: View) {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }

    fun onClickBackButton(view: View) {
        onBackPressed()
    }

    fun onClickPlaySound(view: View) {
        var sound = resources.getIdentifier(this.audioName, "raw", packageName)
        if (!playing && sound != null) {
            playing = true
            val mp = MediaPlayer()
            val uri = Uri.parse("android.resource://$packageName/$sound")
            mp.setDataSource(this, uri)
            mp.prepare()
            mp.setOnPreparedListener { mp.start() }
            mp.setOnCompletionListener { playing = false }
        }
    }

    override fun setContent(card: SignRevisionFlashcard, sign: Sign) {
        this.audioName = sign.audioFileName.toString()
        this.imageName = audioName
    }

    override fun clickSoundButton() {
        playSoundButton.callOnClick()
    }
}