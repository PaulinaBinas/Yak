package com.binas.yak.ui.others.drawing.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.binas.yak.R
import com.binas.yak.ui.base.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_drawing.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [DrawingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DrawingFragment : BaseFragment(), DrawingView {

    @Inject
    internal lateinit var drawingAdapter: DrawingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_drawing, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            DrawingFragment()
    }

    override fun onEraserClick(view: View) {
            draw_view.clearCanvas()
    }
}
