package com.example.nimmaguru2

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class GuruRegisterActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guru_register)

        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        val nameEt = findViewById<EditText>(R.id.nameEt)
        val subjectEt = findViewById<EditText>(R.id.subjectEt)
        val timeEt = findViewById<EditText>(R.id.timeSlotEt)
        val villageEt = findViewById<EditText>(R.id.villageEt)

        val mon = findViewById<CheckBox>(R.id.mon)
        val tue = findViewById<CheckBox>(R.id.tue)
        val wed = findViewById<CheckBox>(R.id.wed)
        val thu = findViewById<CheckBox>(R.id.thu)
        val fri = findViewById<CheckBox>(R.id.fri)

        val saveBtn = findViewById<Button>(R.id.saveBtn)

        saveBtn.setOnClickListener {

            val uid = auth.currentUser?.uid

            if (uid == null) {
                Toast.makeText(this, "Please login first", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val days = arrayListOf<String>()
            if (mon.isChecked) days.add("Monday")
            if (tue.isChecked) days.add("Tuesday")
            if (wed.isChecked) days.add("Wednesday")
            if (thu.isChecked) days.add("Thursday")
            if (fri.isChecked) days.add("Friday")

            val userData = hashMapOf(
                "name" to nameEt.text.toString(),
                "subject" to subjectEt.text.toString(),
                "village" to villageEt.text.toString(),   // ✅ MUST MATCH EXACTLY
                "days" to days,
                "time" to timeEt.text.toString(),
                "role" to "Guru"
            )

            db.collection("users")
                .document(uid)
                .set(userData)
                .addOnSuccessListener {
                    Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
                    finish()
                }
        }
    }
}