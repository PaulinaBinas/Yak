package com.binas.yak.ui.studiedElements.view

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.binas.yak.R
import com.binas.yak.data.model.grammar.Grammar
import com.binas.yak.data.model.grammar.GrammarStudyFlashcard
import com.binas.yak.data.model.sign.Sign
import com.binas.yak.data.model.sign.SignStudyFlashcard
import com.binas.yak.data.model.vocabulary.Vocabulary
import com.binas.yak.data.model.vocabulary.VocabularyStudyFlashcard
import com.binas.yak.ui.base.view.BaseActivity
import com.binas.yak.ui.base.view.BaseView
import com.binas.yak.ui.settings.view.SettingsActivity
import com.binas.yak.ui.studiedElements.details.view.StudiedElementDetailsActivity
import com.binas.yak.ui.studiedElements.element.StudiedElementFragment
import com.binas.yak.ui.studiedElements.interactor.StudiedElementsInteractor
import com.binas.yak.ui.studiedElements.presenter.StudiedElementsPresenter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_studied_elements.*
import kotlinx.android.synthetic.main.fragment_image.*
import javax.inject.Inject

class StudiedElementsActivity : BaseActivity(), StudiedElementsView {

    @Inject
    lateinit var presenter: StudiedElementsPresenter<StudiedElementsView, StudiedElementsInteractor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_studied_elements)
        presenter?.onAttach(this)
        presenter?.start()
    }

    fun onClickSettingsButton(view: View) {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }

    fun onClickBackButton(view: View) {
        onBackPressed()
    }

    fun onClickGoToStudiedElementDetails(view: View) {
        val intent = Intent(this, StudiedElementDetailsActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom)
    }

    override fun addNewSignElement(card: SignStudyFlashcard, sign: Sign) {
        supportFragmentManager.beginTransaction().add(fragmentContainer.id, StudiedElementFragment.newInstance(sign, null, null)).commitAllowingStateLoss()
    }

    override fun addNewVocabularyElement(card: VocabularyStudyFlashcard, vocabulary: Vocabulary) {
        supportFragmentManager.beginTransaction().add(fragmentContainer.id, StudiedElementFragment.newInstance(null, vocabulary, null)).commitAllowingStateLoss()
    }

    override fun addNewGrammarElement(card: GrammarStudyFlashcard, grammar: Grammar) {
        supportFragmentManager.beginTransaction().add(fragmentContainer.id, StudiedElementFragment.newInstance(null, null, grammar)).commitAllowingStateLoss()
    }
}
