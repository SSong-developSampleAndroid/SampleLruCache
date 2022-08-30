package com.ssong_develop.image

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView = findViewById<ImageView>(R.id.test)

        ImageLoader.loadImage("https://picsum.photos/id/237/200/300") {
            Log.d("ssong-develop", "${it}")
            imageView.setImageBitmap(it)
        }
    }
}