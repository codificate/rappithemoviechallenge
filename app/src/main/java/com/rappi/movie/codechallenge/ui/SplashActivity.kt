package com.rappi.movie.codechallenge.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.rappi.movie.codechallenge.BuildConfig
import com.rappi.movie.codechallenge.R

class SplashActivity: AppCompatActivity(){
    private var delayHandler: Handler? = null

    private val rnbl: Runnable = Runnable {
        if (!isFinishing) {
            continueToNextActivity()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        delayHandler = Handler()
        delayHandler!!.postDelayed(rnbl, BuildConfig.SPLASH_DELAY.toLong())

    }

    private fun continueToNextActivity(){
        Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    public override fun onDestroy() {

        if (delayHandler != null) {
            delayHandler!!.removeCallbacks(rnbl)
        }

        super.onDestroy()
    }
}