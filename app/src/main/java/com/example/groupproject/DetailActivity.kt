package com.example.groupproject

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.groupproject.R

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Retrieve candidate details from intent extras
        val candidateName = intent.getStringExtra("name")
        val candidateTitle = intent.getStringExtra("title")
        val largerImageUrl = intent.getStringExtra("largerImageUrl")

        // Display candidate details
        val nameTextView: TextView = findViewById(R.id.name)
        val titleTextView: TextView = findViewById(R.id.title)
        val largerImageView: ImageView = findViewById(R.id.ImageView2)

        nameTextView.text = candidateName
        titleTextView.text = candidateTitle
        Glide.with(this)
            .load(largerImageUrl)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_background)
            .into(largerImageView)

        // Handle connect button click
        val connectButton: Button = findViewById(R.id.connectButton)
        connectButton.setOnClickListener {
            // Implement connection logic here
            // For example, update UI to reflect connection status
            connectButton.text = "Connected" // Example: Change text to reflect connected state
            connectButton.isEnabled = false // Example: Disable button after connecting
        }
    }
}
