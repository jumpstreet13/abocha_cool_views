package com.abocha.coolviewsapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var changeButtonColor: Button
    private lateinit var changeOrientationButton: Button
    private lateinit var supportLandscapeButton: Button
    private lateinit var longText: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        changeButtonColor = findViewById(R.id.change_color_activity)
        changeOrientationButton = findViewById(R.id.change_orientation_activity)
        supportLandscapeButton = findViewById(R.id.support_landscape_activity)
        longText = findViewById(R.id.long_text_activity)
        changeButtonColor.setOnClickListener {
            startActivity(Intent(this@MainActivity, ChangButtonColorActivity::class.java))
        }
        changeOrientationButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, ChangeOrientationActivity::class.java))
        }
        supportLandscapeButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, SupportLandscapeActivity::class.java))
        }
        longText.setOnClickListener {
            startActivity(Intent(this@MainActivity, LongTextActivity::class.java))
        }
    }
}