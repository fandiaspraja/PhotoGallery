package com.fandiaspraja.photogallery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.fandiaspraja.photogallery.ui.gallery.GalleryActivity

class MainActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT:Long = 3000 // 1 sec }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed({
            startActivity(Intent(this, GalleryActivity::class.java))
            finish()

        }, SPLASH_TIME_OUT)

    }
}