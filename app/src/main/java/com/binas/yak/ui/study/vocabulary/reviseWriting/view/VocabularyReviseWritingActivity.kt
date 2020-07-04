package com.binas.yak.ui.study.vocabulary.reviseWriting.view

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.binas.yak.R
import com.binas.yak.data.model.vocabulary.Vocabulary
import com.binas.yak.data.model.vocabulary.VocabularyRevisionFlashcard
import com.binas.yak.ui.base.view.BaseActivity
import com.binas.yak.ui.settings.view.SettingsActivity
import com.binas.yak.ui.study.common.reviseWriting.view.ReviseWritingActivity
import com.binas.yak.ui.study.vocabulary.reviseWriting.interactor.VocabularyReviseWritingInteractor
import com.binas.yak.ui.study.vocabulary.reviseWriting.presenter.VocabularyReviseWritingPresenter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_vocabulary_revise_writing.*
import kotlinx.android.synthetic.main.fragment_image.*
import javax.inject.Inject

class VocabularyReviseWritingActivity : BaseActivity(), VocabularyReviseWritingView {

    @Inject
    lateinit var presenter: VocabularyReviseWritingPresenter<VocabularyReviseWritingView, VocabularyReviseWritingInteractor>
    var playing: Boolean = false
    var soundName: String = ""
    var imageName: String = ""
    var word: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vocabulary_revise_writing)
        presenter?.onAttach(this)
        presenter?.start()
    }

    override fun onStart() {
        super.onStart()
        playSoundButton.callOnClick()
    }

    override fun loadImage() {
        Glide.with(baseContext)
            .load(resources.getIdentifier(imageName, "drawable", this.packageName))
            .into(imageFragment.imageView)
    }

    override fun setContent(card: VocabularyRevisionFlashcard, vocabulary: Vocabulary) {
        this.soundName = vocabulary.audioFileName.toString()
        this.imageName = this.soundName
        this.word = vocabulary.tibetanWord.toString()
    }

    fun onClickGoToReviseWriting(view: View) {
        val intent = Intent(this, ReviseWritingActivity::class.java)
        intent.putExtra("word", word)
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
        if (!playing) {
            playing = true
            val mp: MediaPlayer = MediaPlayer()
            val uri = Uri.parse("android.resource://$packageName/$sound")
            mp.setDataSource(this, uri)
            mp.prepare()
            mp.setOnPreparedListener { mp.start() }
            mp.setOnCompletionListener { playing = false }
        }
    }
}