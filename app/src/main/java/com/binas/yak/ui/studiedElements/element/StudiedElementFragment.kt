package com.binas.yak.ui.studiedElements.element

import android.graphics.Color
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.text.SpannableStringBuilder
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.text.color
import com.airbnb.lottie.LottieDrawable
import com.binas.yak.R
import com.binas.yak.data.model.grammar.Grammar
import com.binas.yak.data.model.sign.Sign
import com.binas.yak.data.model.vocabulary.Vocabulary
import com.binas.yak.ui.others.animation.AnimationFragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_animation.*
import kotlinx.android.synthetic.main.fragment_studied_element.*

/**
 * A simple [Fragment] subclass.
 * Use the [StudiedElementFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StudiedElementFragment : Fragment() {
    private var playing: Boolean = false
    private var sign: Sign? = null
    private var vocabulary: Vocabulary? = null
    private var grammar: Grammar? = null
    private var v: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            sign = it.getParcelable("sign")
            vocabulary= it.getParcelable("vocabulary")
            grammar = it.getParcelable("grammar")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_studied_element, container, false)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        startFragment()
    }

    fun startFragment() {
        super.onResume()
        if(sign != null) {
            this.setupSign()
        } else if(vocabulary != null) {
            this.setupVocabulary()
        } else if(grammar != null) {
            this.setupGrammar()
        }
    }

    private fun setupSign(){
        var fragment = childFragmentManager.fragments.get(0)
        sign?.audioFileName?.let { (fragment as AnimationFragment).setAnimation(it) }
        contentTextView.text = this.sign?.tibetanSign + " (" + (this.sign?.audioFileName ?: "") + ")"
        soundButton.setOnClickListener {
            this.sign?.audioFileName?.let { it1 -> this.onClickPlaySound(it, it1) }
        }
    }

    private fun setupVocabulary(){
        loadImage()
        contentTextView.text = vocabulary?.tibetanWord.toString()
        soundButton.setOnClickListener {
            this.vocabulary?.audioFileName?.let { it1 -> this.onClickPlaySound(it, it1) }
        }
    }

    private fun setupGrammar() {
        grammarSentence.text = SpannableStringBuilder()
            .append(grammar?.firstPartOfSentence)
            .color(Color.rgb(100, 171, 113)) { append(grammar?.grammarPhase) }
            .append(grammar?.secondPartOfSentence)
        contentTextView.text = grammar?.grammarPhase.toString()
        soundButton.setOnClickListener {
            this.grammar?.audioFileName?.let { it1 -> this.onClickPlaySound(it, it1) }
        }
    }

    private fun onClickPlaySound(view: View, fileName: String) {
        var sound = resources.getIdentifier(fileName, "raw", activity?.packageName)
        if (!playing && sound != null) {
            playing = true
            val mp = MediaPlayer()
            val uri = Uri.parse("android.resource://$activity?.packageName/$sound")
            context?.let { mp.setDataSource(it, uri) }
            mp.prepare()
            mp.setOnPreparedListener { mp.start() }
            mp.setOnCompletionListener { playing = false }
        }
    }

    private  fun loadImage() {
        activity?.baseContext?.let {
            Glide.with(it)
                .load(resources.getIdentifier(vocabulary?.audioFileName, "drawable", activity?.packageName))
                .into(studiedElementsImageButton)
        }
        studiedElementsImageButton.layoutParams
        var params = studiedElementsImageButton.layoutParams
        params?.height = ViewGroup.LayoutParams.MATCH_PARENT
        studiedElementsImageButton.layoutParams = params
    }

    companion object {
        @JvmStatic
        fun newInstance(sign: Sign?, vocabulary: Vocabulary?, grammar: Grammar?) =
            StudiedElementFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("sign", sign)
                    putParcelable("vocabulary", vocabulary)
                    putParcelable("grammar", grammar)
                }
            }
    }
}