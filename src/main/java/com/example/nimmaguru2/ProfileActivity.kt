package com.example.nimmaguru2

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val nameText = findViewById<TextView>(R.id.nameText)
        val roleText = findViewById<TextView>(R.id.roleText)
        val subjectText = findViewById<TextView>(R.id.subjectText)
        val availabilityText = findViewById<TextView>(R.id.availabilityText)

        try {

            nameText.text = intent.getStringExtra("name") ?: "N/A"
            roleText.text = intent.getStringExtra("role") ?: "N/A"
            subjectText.text = intent.getStringExtra("subject") ?: "N/A"

            val days = intent.getStringExtra("days")
            val time = intent.getStringExtra("timeSlots")

            availabilityText.text =
                "Days: ${days ?: "Not set"}\nTime: ${time ?: "Not set"}"

        } catch (e: Exception) {
            availabilityText.text = "Error loading profile"
        }
    }
}