package com.example.nimmaguru2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()

        // 🔥 AUTO LOGOUT ON EVERY APP START
        auth.signOut()

        setContentView(R.layout.activity_home)

        val registerBtn = findViewById<Button>(R.id.registerGuruBtn)
        val loginBtn = findViewById<Button>(R.id.guruLoginBtn)
        val viewBtn = findViewById<Button>(R.id.viewGuruBtn)
        val pollBtn = findViewById<Button>(R.id.pollBtn)

        registerBtn.setOnClickListener {
            Toast.makeText(this, "Please login first", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
        }

        loginBtn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        viewBtn.setOnClickListener {
            startActivity(Intent(this, ViewUsersActivity::class.java))
        }

        pollBtn.setOnClickListener {
            startActivity(Intent(this, StudentPollActivity::class.java))
        }
    }
}