package com.binas.yak.ui.study.vocabulary.learn.studyCard.view

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.binas.yak.R
import com.binas.yak.data.model.translation.Translation
import com.binas.yak.data.model.vocabulary.Vocabulary
import com.binas.yak.data.model.vocabulary.VocabularyStudyFlashcard
import com.binas.yak.ui.base.view.BaseActivity
import com.binas.yak.ui.settings.view.SettingsActivity
import com.binas.yak.ui.study.vocabulary.learn.learnWriting.view.LearnVocabularyWritingActivity
import com.binas.yak.ui.study.vocabulary.learn.studyCard.interactor.VocabularyStudyCardInteractor
import com.binas.yak.ui.study.vocabulary.learn.studyCard.presenter.VocabularyStudyCardPresenter
import com.bumptech.glide.Glide
import com.yariksoffice.lingver.Lingver
import kotlinx.android.synthetic.main.activity_vocabulary_study_card.*
import kotlinx.android.synthetic.main.fragment_image.*
import javax.inject.Inject

class VocabularyStudyCardActivity : BaseActivity(), VocabularyStudyCardView {

    private var playing: Boolean = false
    private var imageName: String = ""
    private var soundName: String = ""
    @Inject
    lateinit var presenter: VocabularyStudyCardPresenter<VocabularyStudyCardView, VocabularyStudyCardInteractor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vocabulary_study_card)
        presenter?.onAttach(this)
        presenter?.start()
    }

    override fun loadImage() {
        Glide.with(baseContext)
            .load(resources.getIdentifier(imageName, "drawable", this.packageName))
            .into(imageStudyFragment.imageView)
    }

    override fun setContent(card: VocabularyStudyFlashcard, vocab: Vocabulary, text: Translation?) {
        this.imageName = vocab.audioFileName.toString()
        this.soundName = imageName
        vocabularyTextView.text = vocab.tibetanWord
        if(Lingver.getInstance().getLanguage().equals("pl")) {
            translationTextView.text = text?.polish
        } else {
            translationTextView.text = text?.english
        }
    }

    override fun clickSoundButton() {
        playSoundButton.callOnClick()
    }

    fun onClickGoToLearnNewVocabulary(view: View) {
        val intent: Intent = Intent(this, LearnVocabularyWritingActivity::class.java)
        intent.putExtra("word", vocabularyTextView.text)
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