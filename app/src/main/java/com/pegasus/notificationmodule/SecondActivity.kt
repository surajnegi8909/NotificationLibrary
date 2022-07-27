package com.pegasus.notificationmodule

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        if (intent.hasExtra("title")) {
            val name= intent.getStringExtra("title")
                val textView= findViewById<TextView>(R.id.textView)
            textView.text = "Welcome to $name"
        }
    }
}