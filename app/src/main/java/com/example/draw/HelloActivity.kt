package com.example.draw

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class HelloActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hello)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent =Intent(this,MainActivity::class.java)
            startActivity(intent)
        },3680)


    }
}