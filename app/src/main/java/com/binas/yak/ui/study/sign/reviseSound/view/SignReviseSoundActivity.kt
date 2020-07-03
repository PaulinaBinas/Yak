package com.binas.yak.ui.study.sign.reviseSound.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.binas.yak.R
import com.binas.yak.data.model.sign.Sign
import com.binas.yak.data.model.sign.SignRevisionFlashcard
import com.binas.yak.ui.base.view.BaseActivity
import com.binas.yak.ui.settings.view.SettingsActivity
import com.binas.yak.ui.study.common.pronunciationCheck.view.PronunciationCheckActivity
import com.binas.yak.ui.study.sign.reviseSound.interactor.SignReviseSoundInteractor
import com.binas.yak.ui.study.sign.reviseSound.presenter.SignReviseSoundPresenter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_sign_study_card.*
import kotlinx.android.synthetic.main.fragment_image.*
import javax.inject.Inject

class SignReviseSoundActivity : BaseActivity(), SignReviseSoundView {

    private var audioFileName: String = ""
    private var imageFileName: String = ""
    private var tibetanSign: String = ""
    private var text: String = ""
    @Inject
    lateinit var presenter: SignReviseSoundPresenter<SignReviseSoundView, SignReviseSoundInteractor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_revise_sound)
        presenter?.onAttach(this)
        presenter?.start()
    }

    override fun loadImage() {
        Glide.with(baseContext)
            .load(resources.getIdentifier(this.imageFileName, "drawable", this.packageName))
            .into(imageFragment.imageView)
    }

    fun onClickSettingsButton(view: View) {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }

    fun onClickBackButton(view: View) {
        onBackPressed()
    }

    fun onClickGoToPronunciationCheck(view: View) {
        val intent = Intent(this, PronunciationCheckActivity::class.java)
        intent.putExtra("text", this.text)
        intent.putExtra("image", this.imageFileName)
        intent.putExtra("sound", this.audioFileName)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }

    override fun setContent(card: SignRevisionFlashcard, sign: Sign) {
        this.audioFileName = sign.audioFileName.toString()
        this.imageFileName = audioFileName
        this.tibetanSign = sign.tibetanSign.toString()
        this.text = this.tibetanSign + " (" + audioFileName + ")"
    }
}