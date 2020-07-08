package com.binas.yak.ui.study.vocabulary.reviseSound.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.binas.yak.R
import com.binas.yak.data.model.vocabulary.Vocabulary
import com.binas.yak.data.model.vocabulary.VocabularyRevisionFlashcard
import com.binas.yak.ui.base.view.BaseActivity
import com.binas.yak.ui.main.view.MainActivity
import com.binas.yak.ui.settings.view.SettingsActivity
import com.binas.yak.ui.study.common.pronunciationCheck.view.PronunciationCheckActivity
import com.binas.yak.ui.study.vocabulary.reviseSound.interactor.VocabularyReviseSoundInteractor
import com.binas.yak.ui.study.vocabulary.reviseSound.presenter.VocabularyReviseSoundPresenter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_vocabulary_revise_sound.*
import kotlinx.android.synthetic.main.fragment_image.*
import javax.inject.Inject

class VocabularyReviseSoundActivity : BaseActivity(), VocabularyReviseSoundView {

    @Inject
    lateinit var presenter: VocabularyReviseSoundPresenter<VocabularyReviseSoundView, VocabularyReviseSoundInteractor>
    private var imageName: String = ""
    private var tibetanWord: String = ""
    private var audioName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vocabulary_revise_sound)
        presenter?.onAttach(this)
        presenter?.start()
    }

    override fun setContent(card: VocabularyRevisionFlashcard, vocabulary: Vocabulary) {
        this.audioName = vocabulary.audioFileName.toString()
        this.imageName = this.audioName
        this.tibetanWord = vocabulary.tibetanWord.toString()
    }

    override fun loadImage() {
        Glide.with(baseContext)
            .load(resources.getIdentifier(imageName, "drawable", this.packageName))
            .into(imageFragment.imageView)
    }

    fun onClickSettingsButton(view: View) {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }

    fun onClickBackButton(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_out_bottom)
        finish()
    }

    fun onClickGoToPronunciationCheck(view: View) {
        val intent = Intent(this, PronunciationCheckActivity::class.java)
        intent.putExtra("text", this.tibetanWord)
        intent.putExtra("image", this.imageName)
        intent.putExtra("sound", this.audioName)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }
}