package com.samuelchowi.bellatech.secondary

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.samuelchowi.bellatech.R
import com.samuelchowi.bellatech.third.ThirdActivity
import kotlinx.android.synthetic.main.activity_secondary.*

class SecondaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondary)

        lifecycle.addObserver(ShakeActionTracker(this))
        btnNext.setOnClickListener { startActivity(Intent(this, ThirdActivity::class.java)) }
    }
}