package com.abocha.coolviewsapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.abocha.coolviewsapplication.views.RiskPointsView

class MainActivity : AppCompatActivity() {

    companion object {
        var riskPointsCalled: Boolean = false
    }

    private lateinit var changeButtonColor: Button
    private lateinit var changeOrientationButton: Button
    private lateinit var supportLandscapeButton: Button
    private lateinit var longText: Button
    private lateinit var toastButton: Button
    private lateinit var riskPointView: RiskPointsView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        changeButtonColor = findViewById(R.id.change_color_activity)
        changeOrientationButton = findViewById(R.id.change_orientation_activity)
        supportLandscapeButton = findViewById(R.id.support_landscape_activity)
        toastButton = findViewById(R.id.toast_activity)
        longText = findViewById(R.id.long_text_activity)
        riskPointView = findViewById(R.id.risk_view)
        if (!riskPointsCalled) {
            riskPointView.setRiskPoints(3)
            riskPointsCalled = true
        }
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
        toastButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, ToastToSnackActivity::class.java))
        }
    }
}