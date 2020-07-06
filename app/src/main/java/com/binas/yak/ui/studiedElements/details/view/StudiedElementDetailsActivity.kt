package com.binas.yak.ui.studiedElements.details.view

import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import android.widget.LinearLayout
import androidx.core.text.color
import com.airbnb.lottie.LottieDrawable
import com.binas.yak.R
import com.binas.yak.ui.base.view.BaseActivity
import com.binas.yak.ui.settings.view.SettingsActivity
import com.binas.yak.ui.studiedElements.calendar.view.CalendarActivity
import com.binas.yak.ui.studiedElements.details.interactor.StudiedElementDetailsInteractor
import com.binas.yak.ui.studiedElements.details.presenter.StudiedElementDetailsPresenter
import com.bumptech.glide.Glide
import com.yariksoffice.lingver.Lingver
import kotlinx.android.synthetic.main.activity_studied_element_details.*
import javax.inject.Inject

class StudiedElementDetailsActivity : BaseActivity(), StudiedElementDetailsView {

    @Inject
    lateinit var presenter: StudiedElementDetailsPresenter<StudiedElementDetailsView, StudiedElementDetailsInteractor>
    private var playing: Boolean = false
    private var audioFileName: String = ""
    private var id: Long = 0L
    private var type: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_studied_element_details)
        if(intent.hasExtra("signId")) {
            id = intent.getLongExtra("signId", 0L)
            type = "sign"
            initSign()
        } else if(intent.hasExtra("vocabularyId")){
            id = intent.getLongExtra("vocabularyId", 0L)
            type = "vocabulary"
            initVocabulary()
        } else if(intent.hasExtra("grammarId")){
            id = intent.getLongExtra("grammarId", 0L)
            type = "grammar"
            initGrammar()
        }
        playSoundButton.callOnClick()
    }

    private fun initSign() {
        audioFileName = intent.getStringExtra("audio")
        loadAnimation(audioFileName)
        term.text = intent.getStringExtra("sign")
        translation.text = audioFileName
    }

    private fun initVocabulary() {
        audioFileName = intent.getStringExtra("audio")
        term.text = intent.getStringExtra("word")
        var text = presenter.getTranslation(intent.getLongExtra("translationId", 0L))
        if(Lingver.getInstance().getLanguage().equals("pl")) {
            translation.text = text?.polish
        } else {
            translation.text = text?.english
        }
        loadImage()
    }

    private fun initGrammar() {
        audioFileName = intent.getStringExtra("audio")
        term.text = intent.getStringExtra("grammar")
        var params = sentence.layoutParams
        params.width = LinearLayout.LayoutParams.MATCH_PARENT
        params.height = LinearLayout.LayoutParams.WRAP_CONTENT
        sentence.layoutParams = params
        sentence.text = SpannableStringBuilder()
            .append(intent.getStringExtra("sentenceStart"))
            .color(Color.rgb(100, 171, 113)) { append(intent.getStringExtra("grammar")) }
            .append(intent.getStringExtra("sentenceEnd"))
        var text = presenter.getTranslation(intent.getLongExtra("translationId", 0L))
        if(Lingver.getInstance().getLanguage().equals("pl")) {
            translation.text = text?.polish
        } else {
            translation.text = text?.english
        }
    }

    private fun loadAnimation(imgName: String) {
        animation.setAnimation("animations/" + imgName + ".json")
        animation.repeatCount = LottieDrawable.INFINITE
        animation.speed = 4f
        var params = animation.layoutParams
        params.width = LinearLayout.LayoutParams.MATCH_PARENT
        params.height = LinearLayout.LayoutParams.WRAP_CONTENT
        animation.layoutParams = params
        animation.playAnimation()
    }

    private fun loadImage() {
        var params = image.layoutParams
        params.width = LinearLayout.LayoutParams.MATCH_PARENT
        params.height = LinearLayout.LayoutParams.WRAP_CONTENT
        image.layoutParams = params
        Glide.with(baseContext)
            .load(resources.getIdentifier(audioFileName, "drawable", this.packageName))
            .into(image)
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
        var sound = resources.getIdentifier(audioFileName, "raw", packageName)
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

    fun onClickGoToCalendar(view: View) {
        val intent = Intent(this, CalendarActivity::class.java)
        var days = presenter?.getDaysTillRevision(type, id)
        intent.putExtra("days", days)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }
}