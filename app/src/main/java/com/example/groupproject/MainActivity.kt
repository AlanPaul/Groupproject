package com.example.groupproject

import PostAdapter
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PostAdapter
    private lateinit var databaseRef: DatabaseReference
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var menuButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //calling splash screen
        Thread.sleep(3000)
        installSplashScreen()

        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = PostAdapter(mutableListOf())
        recyclerView.adapter = adapter

        // Initialize Firebase database reference
        databaseRef = FirebaseDatabase.getInstance().reference.child("posts")

        // Load posts from Firebase Realtime Database
        loadPosts()

        // Initialize navigation drawer
        drawerLayout = findViewById(R.id.menubar)
        navView = findViewById(R.id.nav)
        menuButton = findViewById(R.id.menu)

        val toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    Toast.makeText(applicationContext, "Home Clicked", Toast.LENGTH_SHORT).show()
                    // Close the drawer
                    drawerLayout.closeDrawers()
                }
                R.id.post -> {
                    Toast.makeText(applicationContext, "post", Toast.LENGTH_SHORT).show()
                    drawerLayout.closeDrawers()

                }
                R.id.candidate -> {
                    val intent = Intent(this, CandidateActivity::class.java)
                    startActivity(intent)

                    drawerLayout.closeDrawers()
                }
                R.id.logout -> {
                    Toast.makeText(applicationContext, "Logging Out...", Toast.LENGTH_SHORT).show()

                    drawerLayout.closeDrawers()
                }
            }
            true
        }

        // Set OnClickListener for the menu button
        menuButton.setOnClickListener {
            if (drawerLayout.isDrawerOpen(navView)) {
                drawerLayout.closeDrawer(navView)
            } else {
                drawerLayout.openDrawer(navView)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            if (drawerLayout.isDrawerOpen(navView)) {
                drawerLayout.closeDrawer(navView)
            } else {
                drawerLayout.openDrawer(navView)
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun navigateToCandidates(view: View) {
        val intent = Intent(this, CandidateActivity::class.java)
        startActivity(intent)
    }

    private fun loadPosts() {
        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val postsList = mutableListOf<Post>()
                for (postSnapshot in snapshot.children) {
                    val post = postSnapshot.getValue(Post::class.java)
                    post?.let { postsList.add(it) }
                }
                adapter.submitList(postsList)
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}
