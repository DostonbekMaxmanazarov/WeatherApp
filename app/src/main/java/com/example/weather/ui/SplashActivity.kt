package com.example.weather.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.weather.R
import com.example.weather.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    lateinit var binding: ActivitySplashBinding
    private var top: Animation? = null
    private var bottom: Animation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySplashBinding.inflate(layoutInflater)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        top = AnimationUtils.loadAnimation(this, R.anim.top)
        bottom = AnimationUtils.loadAnimation(this, R.anim.bottom)

        binding.lottie.animation = top
        binding.tv.animation = bottom

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 3000)

    }
}