package com.example.groupproject

import CandidateAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class CandidateActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CandidateAdapter
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_candidate)

        recyclerView = findViewById(R.id.recyclerView2)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CandidateAdapter()
        recyclerView.adapter = adapter

        // Initialize Firebase database reference
        databaseReference = FirebaseDatabase.getInstance().reference.child("candidates")

        // Load candidates from Firebase
        loadCandidates()
    }

    private fun loadCandidates() {
        val candidatesList = mutableListOf<Candidate>()
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                candidatesList.clear()
                for (postSnapshot in snapshot.children) {
                    val candidate = postSnapshot.getValue(Candidate::class.java)
                    candidate?.let { candidatesList.add(it) }
                }
                adapter.submitList(candidatesList)
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }
}



