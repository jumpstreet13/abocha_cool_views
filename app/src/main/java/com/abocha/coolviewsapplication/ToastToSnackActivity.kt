package com.abocha.coolviewsapplication

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ToastToSnackActivity : AppCompatActivity() {

    private lateinit var toastButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toast_to_snack)
        toastButton = findViewById(R.id.toast_button)
        toastButton.setOnClickListener {
            Toast.makeText(this@ToastToSnackActivity, "Hello Sir", Toast.LENGTH_SHORT).show()
        }
    }
}