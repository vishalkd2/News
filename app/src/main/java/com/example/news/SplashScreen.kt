package com.example.news

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

class SplashScreen : AppCompatActivity() {
    // Duration of splash screen delay in milliseconds
    private val SPLASH_DELAY: Long = 3000 // 3 seconds

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        val splashImage = findViewById<ImageView>(R.id.splachscreen)
        Glide.with(this)
            .asGif()
            .load(R.drawable.splash_screen)
            .apply(RequestOptions().centerCrop())
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(splashImage)

        // Delay for the specified duration and then start the main activity
        Handler().postDelayed({
            // Start the main activity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            // Finish the splash activity
            finish()
        }, SPLASH_DELAY)
    }
}