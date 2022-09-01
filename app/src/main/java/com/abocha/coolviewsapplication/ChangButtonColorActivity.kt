package com.abocha.coolviewsapplication

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ChangButtonColorActivity : AppCompatActivity() {

    private lateinit var button: Button
    private var counter: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_text_color)
        button = findViewById(R.id.change_color_button)
        button.setOnClickListener {
            counter++
            button.text = "Вы нажали меня $counter раз"
        }
    }
}