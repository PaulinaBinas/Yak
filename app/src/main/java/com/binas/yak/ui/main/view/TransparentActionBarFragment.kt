package com.binas.yak.ui.main.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.binas.yak.R

/**
 * A simple [Fragment] subclass.
 * Use the [TransparentActionBarFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TransparentActionBarFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transparent_action_bar, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment TransparentActionBarFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TransparentActionBarFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}