package com.example.nimmaguru2

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class GuruPollViewActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PollAdapter
    private val pollList = ArrayList<PollModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guru_poll_view)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = PollAdapter(pollList)
        recyclerView.adapter = adapter

        loadPollData()
    }

    private fun loadPollData() {

        FirebaseFirestore.getInstance()
            .collection("polls")
            .get()
            .addOnSuccessListener { result ->

                pollList.clear()

                for (doc in result) {

                    val subject = doc.getString("subject") ?: "Unknown"
                    val count = doc.getLong("count") ?: 0L

                    pollList.add(PollModel(subject, count))
                }

                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener {

                Toast.makeText(this, "Failed to load polls", Toast.LENGTH_SHORT).show()
            }
    }
}