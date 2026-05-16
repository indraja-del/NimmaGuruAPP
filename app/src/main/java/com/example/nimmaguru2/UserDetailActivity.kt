package com.example.nimmaguru2

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class UserDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        val name = findViewById<TextView>(R.id.nameDetail)
        val role = findViewById<TextView>(R.id.roleDetail)
        val subject = findViewById<TextView>(R.id.subjectDetail)
        val availabilityText = findViewById<TextView>(R.id.availabilityText)

        name.text = intent.getStringExtra("name")
        role.text = intent.getStringExtra("role")
        subject.text = intent.getStringExtra("subject")

        val availability = intent.getSerializableExtra("availability") as? HashMap<*, *>

        val days = availability?.get("days") as? List<*>
        val timeSlots = availability?.get("timeSlots") as? List<*>

        availabilityText.text =
            "Days: ${days?.joinToString(", ")}\nTime: ${timeSlots?.joinToString(", ")}"
    }
}