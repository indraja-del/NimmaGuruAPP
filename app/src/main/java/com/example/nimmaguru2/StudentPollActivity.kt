package com.example.nimmaguru2

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class StudentPollActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_poll)

        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        val input = findViewById<EditText>(R.id.subjectInput)
        val btn = findViewById<Button>(R.id.interestBtn)
        val result = findViewById<TextView>(R.id.resultText)

        btn.setOnClickListener {

            val subject = input.text.toString().trim()
            val uid = auth.currentUser?.uid ?: "guest"

            if (subject.isEmpty()) {
                Toast.makeText(this, "Enter subject", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val docRef = db.collection("polls").document(subject)

            db.runTransaction { transaction ->

                val snapshot = transaction.get(docRef)

                val exists = snapshot.exists()

                val voters = if (exists)
                    snapshot.get("voters") as? HashMap<String, Boolean> ?: hashMapOf()
                else
                    hashMapOf()

                if (voters.containsKey(uid)) {
                    throw Exception("Already voted")
                }

                voters[uid] = true

                val currentCount = if (exists)
                    snapshot.getLong("count") ?: 0
                else
                    0

                transaction.set(docRef, mapOf(
                    "subject" to subject,
                    "count" to currentCount + 1,
                    "voters" to voters
                ))
            }.addOnSuccessListener {

                docRef.get().addOnSuccessListener {
                    val count = it.getLong("count") ?: 0
                    result.text = "Total Interest: $count"
                }

            }.addOnFailureListener {
                Toast.makeText(this, "Already voted", Toast.LENGTH_SHORT).show()
            }
        }
    }
}