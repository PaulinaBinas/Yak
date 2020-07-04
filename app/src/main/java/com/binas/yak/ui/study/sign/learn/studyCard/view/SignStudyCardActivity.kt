package com.binas.yak.ui.study.sign.learn.studyCard.view

import android.content.Intent
import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.airbnb.lottie.LottieDrawable
import com.binas.yak.R
import com.binas.yak.data.model.sign.Sign
import com.binas.yak.data.model.sign.SignStudyFlashcard
import com.binas.yak.data.model.translation.Translation
import com.binas.yak.ui.base.view.BaseActivity
import com.binas.yak.ui.settings.view.SettingsActivity
import com.binas.yak.ui.study.sign.learn.studyCard.interactor.SignStudyCardInteractor
import com.binas.yak.ui.study.sign.learn.studyCard.presenter.SignStudyCardPresenter
import com.binas.yak.ui.study.sign.learn.writing.view.LearnSignWritingActivity
import com.bumptech.glide.Glide
import com.yariksoffice.lingver.Lingver
import kotlinx.android.synthetic.main.activity_sign_study_card.*
import kotlinx.android.synthetic.main.activity_sign_study_card.imageFragment
import kotlinx.android.synthetic.main.fragment_image.*
import javax.inject.Inject

class SignStudyCardActivity : BaseActivity(), SignStudyCardView {

    private var playing: Boolean = false
    private var imgName: String = ""
    private var soundName: String = ""
    private var card: SignStudyFlashcard? = null
    private var sign: Sign? = null
    private var translation: Translation? = null
    @Inject
    lateinit var presenter: SignStudyCardPresenter<SignStudyCardView, SignStudyCardInteractor>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_study_card)
        presenter.onAttach(this)
        presenter?.start()
    }

    override fun loadImage() {
//        Glide.with(this)
//            .load(resources.getIdentifier(imgName, "drawable", this.packageName))
//            .into(imageFragment.imageView)
        imageFragment.animationView.setAnimation("animations/" + this.imgName + ".json")
        imageFragment.animationView.repeatCount = LottieDrawable.INFINITE
        imageFragment.animationView.speed = 4f
        imageFragment.animationView.playAnimation()
    }

    override fun clickPlaySound() {
        playSoundButton.callOnClick()
    }

    fun onClickGoToLearnNewSign(view: View) {
        val intent: Intent = Intent(this, LearnSignWritingActivity::class.java)
        intent.putExtra("imageName", imgName)
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
        var sound = resources.getIdentifier(soundName, "raw", packageName)
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

    override fun setContent(card: SignStudyFlashcard?, sign: Sign?, translation: Translation?) {
        signText.text = sign!!.tibetanSign + " (" + sign!!.audioFileName + ")"
        if(Lingver.getInstance().getLanguage() == "pl") {
            description.text = translation?.polish
        } else {
            description.text = translation?.english
        }
        imgName = sign!!.audioFileName.toString()
        soundName = sign!!.audioFileName.toString()
    }
}