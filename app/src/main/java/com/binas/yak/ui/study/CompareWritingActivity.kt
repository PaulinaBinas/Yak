package com.binas.yak.ui.study

import android.R.attr.data
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.binas.yak.R
import kotlinx.android.synthetic.main.activity_compare_writing.*
import kotlinx.android.synthetic.main.fragment_image.*


class CompareWritingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compare_writing)
    }

    override fun onStart() {
        super.onStart()
        val bytes = intent.getByteArrayExtra("bitmap")
        val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
        imageFragment.imageView.setImageBitmap(bitmap)
    }
}