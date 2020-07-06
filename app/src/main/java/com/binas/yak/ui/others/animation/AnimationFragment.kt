package com.binas.yak.ui.others.animation

import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.airbnb.lottie.LottieDrawable
import com.binas.yak.R
import com.binas.yak.data.model.sign.Sign
import com.binas.yak.ui.studiedElements.view.StudiedElementsActivity
import kotlinx.android.synthetic.main.fragment_animation.*
import kotlinx.android.synthetic.main.fragment_studied_element.*


/**
 * A simple [Fragment] subclass.
 * Use the [AnimationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AnimationFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_animation, container, false)
    }

    fun setAnimation(fileName: String) {
        animationFragment.animationView.setAnimation("animations/$fileName.json")
        animationFragment.animationView.repeatCount = LottieDrawable.INFINITE
        animationFragment.animationView.speed = 4f
        animationFragment.animationView.playAnimation()
        var params = animationFragment.view?.layoutParams
        params?.height = FrameLayout.LayoutParams.MATCH_PARENT
        params?.width = Resources.getSystem().displayMetrics.widthPixels.minus(200)
        animationFragment.view?.layoutParams = params
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment AnimationFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AnimationFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}