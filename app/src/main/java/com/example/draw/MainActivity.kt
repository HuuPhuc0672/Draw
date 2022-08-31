package com.example.draw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val  drawPath: DrawPath= findViewById(R.id.layout_draw);
        val btnTien : ImageView = findViewById(R.id.img_redo)
        val btnQuay : ImageView = findViewById(R.id.img_undo)


        btnTien.setOnClickListener {
            drawPath.setRedo()
        }
        btnQuay.setOnClickListener {
            drawPath.setUndo()

        }
    }
}