package com.example.nimmaguru2

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class ViewUsersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_users)

        val nameText = findViewById<TextView>(R.id.nameText)
        val subjectText = findViewById<TextView>(R.id.subjectText)
        val timeText = findViewById<TextView>(R.id.timeText)
        val daysText = findViewById<TextView>(R.id.daysText)
        val villageText = findViewById<TextView>(R.id.villageText)

        val db = FirebaseFirestore.getInstance()

        db.collection("users")
            .whereEqualTo("role", "Guru")
            .get()
            .addOnSuccessListener { result ->

                if (result.isEmpty) {
                    nameText.text = getString(R.string.no_guru_found)
                    return@addOnSuccessListener
                }

                val doc = result.documents[0]

                nameText.text = doc.getString("name") ?: getString(R.string.not_set)
                subjectText.text = doc.getString("subject") ?: getString(R.string.not_set)
                timeText.text = doc.getString("time") ?: getString(R.string.not_set)
                villageText.text = doc.getString("village") ?: getString(R.string.not_set)

                // ✅ SAFE CAST FIX
                val daysList = doc.get("days") as? List<*>

                daysText.text = if (daysList != null) {
                    daysList.filterIsInstance<String>().joinToString(", ")
                } else {
                    getString(R.string.not_set)
                }
            }
    }
}