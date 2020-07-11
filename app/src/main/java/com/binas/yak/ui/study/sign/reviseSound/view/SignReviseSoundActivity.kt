package com.binas.yak.ui.study.sign.reviseSound.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.airbnb.lottie.LottieDrawable
import com.binas.yak.R
import com.binas.yak.data.model.sign.Sign
import com.binas.yak.data.model.sign.SignRevisionFlashcard
import com.binas.yak.ui.base.view.BaseActivity
import com.binas.yak.ui.main.view.MainActivity
import com.binas.yak.ui.settings.view.SettingsActivity
import com.binas.yak.ui.study.common.pronunciationCheck.view.PronunciationCheckActivity
import com.binas.yak.ui.study.sign.reviseSound.interactor.SignReviseSoundInteractor
import com.binas.yak.ui.study.sign.reviseSound.presenter.SignReviseSoundPresenter
import kotlinx.android.synthetic.main.activity_sign_revise_sound.animationFragment
import kotlinx.android.synthetic.main.fragment_animation.*
import javax.inject.Inject

class SignReviseSoundActivity : BaseActivity(), SignReviseSoundView {

    private var audioFileName: String = ""
    private var imageFileName: String = ""
    private var tibetanSign: String = ""
    private var text: String = ""
    private var id: Long? = null
    @Inject
    lateinit var presenter: SignReviseSoundPresenter<SignReviseSoundView, SignReviseSoundInteractor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_revise_sound)
        id = intent.getLongExtra("id", -1L)
        presenter?.onAttach(this)
        presenter?.start(id!!)
    }

    override fun loadAnimation() {
        animationFragment.animationView.setAnimation("animations/" + this.imageFileName + ".json")
        animationFragment.animationView.repeatCount = LottieDrawable.INFINITE
        animationFragment.animationView.speed = 1f
        animationFragment.animationView.playAnimation()
    }

    fun onClickSettingsButton(view: View) {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }

    fun onClickBackButton(view: View) {
        this.onBackPressed()
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_out_bottom)
        finish()
    }

    fun onClickGoToPronunciationCheck(view: View) {
        val intent = Intent(this, PronunciationCheckActivity::class.java)
        intent.putExtra("text", this.text)
        intent.putExtra("image", this.imageFileName)
        intent.putExtra("sound", this.audioFileName)
        intent.putExtra("animated", true)
        intent.putExtra("type", "sign")
        intent.putExtra("id", id)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }

    override fun setContent(card: SignRevisionFlashcard, sign: Sign) {
        this.audioFileName = sign.audioFileName.toString()
        this.imageFileName = audioFileName
        this.tibetanSign = sign.tibetanSign.toString()
        this.text = this.tibetanSign + " (" + audioFileName + ")"
        this.id = card.id
    }
}